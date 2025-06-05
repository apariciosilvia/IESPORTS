package com.iesports.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.MatchServiceImpl;
import com.iesports.dao.service.impl.SportServiceImpl;
import com.iesports.dao.service.impl.TeamServiceImpl;
import com.iesports.dao.service.impl.TournamentServiceImpl;
import com.iesports.dto.MatchModifyTournamentDTO;
import com.iesports.dto.TournamentAddDTO;
import com.iesports.dto.TournamentAdminDTO;
import com.iesports.dto.TournamentFilterDTO;
import com.iesports.dto.TournamentModifyDTO;
import com.iesports.enums.RoundMatchEnum;
import com.iesports.enums.StateTournamentEnum;
import com.iesports.model.Match;
import com.iesports.model.Sport;
import com.iesports.model.Team;
import com.iesports.model.Tournament;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tournament")
@Tag(name = "Gestión de torneos", description = "Endpoints para la gestión de torneos")
public class TournamentController {

	RoundMatchEnum currentRoundState = null;
	@Autowired
	private TournamentServiceImpl tournamentS;
	@Autowired
	private MatchServiceImpl matchS;
	@Autowired
	TeamServiceImpl teamS;
	@Autowired
	SportServiceImpl sportS;
	
	@PostMapping("/getTournaments")
	public ResponseEntity<?> getTournaments(@RequestBody TournamentFilterDTO filterDTO) {
	    Integer sport_id = filterDTO.getSport_id();
	    String date = filterDTO.getDate();

	    List<Tournament> tournaments = null;
	    Map<String, String> errores = new HashMap<>();

	    System.out.println("Parámetros recibidos - sport_id: " + sport_id + ", date: " + date);

	    if (sport_id != null && date != null) {
	        System.out.println("Buscando torneos por sport_id y fecha");
	        tournaments = tournamentS.findTournamentsBySportIdAndDate(sport_id, date);
	    } else if (sport_id != null) {
	        System.out.println("Buscando torneos por sport_id");
	        tournaments = tournamentS.findTournamentsBySportId(sport_id);
	    } else if (date != null) {
	        System.out.println("Buscando torneos por fecha");
	        tournaments = tournamentS.findTournamentsByDate(date);
	    } else {
	        System.out.println("Obteniendo todos los torneos");
	        tournaments = tournamentS.getTournaments();

	        tournaments = tournaments.stream()
	                .sorted((t1, t2) -> t2.getDate().compareTo(t1.getDate()))
	                .collect(Collectors.toList());

	        System.out.println("Torneos ordenados de más nuevo a más viejo");
	    }

	    System.out.println("Cantidad de torneos encontrados: " + (tournaments != null ? tournaments.size() : 0));

	    if (tournaments == null) {
	        System.err.println("No se encontraron torneos");
	        errores.put("error", "No se encontraron torneos con los parámetros indicados");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
	    }

	    return ResponseEntity.ok(tournaments);
	}


	@GetMapping("/getYears")
	public ResponseEntity<?> getDates() {
	    System.out.println("Solicitud recibida para obtener fechas de torneos");

	    List<String> fechas = tournamentS.findTournamentsDates();

	    if (fechas == null || fechas.isEmpty()) {
	        System.err.println("No se encontraron fechas de torneos");
	        Map<String, String> errores = new HashMap<>();
	        errores.put("error", "No se encontraron fechas de torneos");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
	    }

	    System.out.println("Se encontraron " + fechas.size() + " fechas de torneos");
	    return ResponseEntity.ok(fechas);
	}
	
	@GetMapping("/getTeamsByTournamentId")
	public ResponseEntity<?> getTeamsByTournamentId(){
		List<TournamentAdminDTO> result = new ArrayList<TournamentAdminDTO>();
		
		List<Tournament> tournaments = tournamentS.getTournaments();
		
	    tournaments.sort(
	            Comparator.comparing(Tournament::getDate, Comparator.nullsLast(Comparator.reverseOrder()))
	                      .thenComparing(Tournament::getName, Comparator.nullsLast(String::compareToIgnoreCase))
	        );
		
		for (Tournament currentTournament : tournaments)
		{
			TournamentAdminDTO newTournamentAdmin = new TournamentAdminDTO();
			List<Team> teamsCurrentTournament = teamS.getTeamsByTournamentId(currentTournament.getId());
			
			newTournamentAdmin.setTournament(currentTournament);
			newTournamentAdmin.setTeams(teamsCurrentTournament);

			result.add(newTournamentAdmin);
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PostMapping("/addTournament")
	public ResponseEntity<?> addTournament(@Valid @RequestBody  TournamentAddDTO tournamentDTO){
		
		String currentYear = null;
		Map<String, String> errores = new HashMap<>();
        
		
		if(tournamentDTO.getSportId() == 0 || tournamentDTO.getSportId().toString() == null || tournamentDTO.getSportId().toString().isEmpty())
		{
			errores.put("error", "No se ha encontrado el deporte");
			System.err.println("No se ha encontrado el deporte");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
		}
		
		if (tournamentDTO.getNumTeams() != 4 && tournamentDTO.getNumTeams() != 8 && tournamentDTO.getNumTeams() != 16)
		{
			errores.put("error", "El número de equipos es incorrecto");
			System.err.println("El número de equipos es incorrecto");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
		}
		
		//EN CASO DE ESTAR EN EL RANGO DE SEPTIEMBRE-DICIEMBRE, EL AÑO QUE SE GENERA
		//SEGUIRÁ LA SIGUIENTE ESTRUCTURA --> año actual/año siguiente
		if(LocalDate.now().getMonthValue() >= 9 && LocalDate.now().getMonthValue() <= 12)
		{
			String currentNextYear = String.valueOf((LocalDate.now().getYear()%100)+1);
			currentYear = String.valueOf(LocalDate.now().getYear())+"/"+currentNextYear;
		}
		//EN CASO CONTRARIO UTILIZARÁ ESTA ESTRUCTURA --> AÑO PASADO/AÑO ACTUAL
		else
		{
			String currentNextYear = String.valueOf((LocalDate.now().getYear()%100));
			currentYear = String.valueOf(LocalDate.now().getYear()-1)+"/"+currentNextYear;
		}
		
		Sport currentSport = sportS.getSportById(tournamentDTO.getSportId());
		Tournament currentTournament = new Tournament(null,tournamentDTO.getName(),currentYear, StateTournamentEnum.PENDIENTE, currentSport);
		
		//Primero añadimos el nuevo torneo
		currentTournament = tournamentS.saveTournament(currentTournament);
		
		System.out.println("SE HA GUARDADO EL TORNEO"+currentTournament);
		
		//Calculamos el número de partidos
		int numMatches = tournamentDTO.getNumTeams() / 2;
		
		if(tournamentDTO.getNumTeams() == 4)
			currentRoundState = RoundMatchEnum.SEMIFINAL;
		if(tournamentDTO.getNumTeams() == 8)
			currentRoundState = RoundMatchEnum.CUARTOS_FINAL;
		if(tournamentDTO.getNumTeams() == 16)
			currentRoundState = RoundMatchEnum.OCTAVOS;
		
		for (int i = 0; i < numMatches; i++)
		{
			Team currentTeam1 = teamS.getTeam(tournamentDTO.getMatches().get(i).getTeam1Id());
			Team currentTeam2 = teamS.getTeam(tournamentDTO.getMatches().get(i).getTeam2Id());
			
			Match currentMatch = new Match(null, tournamentDTO.getMatches().get(i).getMatchDate(), currentRoundState, currentTournament, currentTeam1,currentTeam2 , 0, 0 ,null);
			
			currentMatch = matchS.saveMatch(currentMatch);
			System.out.println("SE HA GUARDADO EL PARTIDO" + currentMatch);
		}
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(currentTournament);
	}
	
	@GetMapping("/getTournamentById")
	@Operation(
	    summary = "Obtener torneo por ID",
	    description = "Devuelve el torneo correspondiente al ID proporcionado"
	)
	public ResponseEntity<Tournament> getTournamentById(@RequestParam Long tournamentId) {
	    Tournament tournament = tournamentS.getTournamentById(tournamentId);

	    if (tournament == null) {
	        System.err.println("No se encontro el torneo con el id " + tournamentId);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tournament);
	    }

	    return ResponseEntity.status(HttpStatus.OK).body(tournament);
	}
	
	@PostMapping("/deleteTournament")
	public ResponseEntity<?> deleteTournament(@RequestParam Long tournamentId){
		
		Tournament currentTournament = tournamentS.getTournamentById(tournamentId);
		
		List<Match> matchesFromTournament = new ArrayList<>();
		
		matchesFromTournament = matchS.getMatchesByTournamentId(tournamentId);
		
		for (Match currentMatch : matchesFromTournament)
		{
			matchS.deleteMatch(currentMatch);
		}
		
		tournamentS.deleteTournament(currentTournament);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	@PostMapping("/modifyTournament")
	public ResponseEntity<?> modifyTournament(@Valid @RequestBody TournamentModifyDTO tournamentDTO ){
		System.err.println(tournamentDTO.toString());
		//-----------VARIABLES PARA UTILIZAR EN TORNEO-----------//
		//PRIMERO RECOGEMOS EL TORNEO MEDIANTE EL ID
		Tournament currentTournament = new Tournament();
		currentTournament = tournamentS.getTournamentById(tournamentDTO.getTournamentId());
		//-------------------------------------------------------//
		
		//-----------VARIABLES PARA UTILIZAR EN PARTIDOS-----------//
		List<Match> matchesWinnersEnded = new ArrayList<>();
		int contTeamPerMatch = 0;
		Match newMatchNextRound = new Match(0L,null,null,currentTournament,null,null,0,0,null);
		//-------------------------------------------------------//
		
		//EN CASO DE SER PENDIENTE, SE PODRÁ MODIFICAR EL NOMBRE DEL TORNEO Y EL DEPORTE
		if(currentTournament.getState() == StateTournamentEnum.PENDIENTE)
		{
			boolean tournamentIsModified = false;
	
			//TODO CORREGIR currentTournament.getName()
			if(!currentTournament.getName().equals(tournamentDTO.getTournamentNameModified()) && currentTournament.getName() != null)
			{
				currentTournament.setName(tournamentDTO.getTournamentNameModified());
				tournamentIsModified = true;
			}
				
			if(currentTournament.getSport().getId() != tournamentDTO.getSportModifiedId())
			{
				Sport currentSport = sportS.getSportById(tournamentDTO.getSportModifiedId());
				
				if(currentSport != null)
				{
					currentTournament.setSport(currentSport);
					tournamentIsModified = true;
				}
				else
				{
					System.err.println("No se encontro el deporte con el id " + tournamentDTO.getSportModifiedId());
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(currentSport);
				}
			}
			
			//FINALMENTE EN CASO DE QUE HAYA MODIFICACIÓN, SE ACTUALIZARÁN LOS DATOS DEL TORNEO
			if(tournamentIsModified == true) {
				currentTournament = tournamentS.updateTournament(currentTournament);
			}
			
		}
		
		//MIENTRAS EL TORNEO NO HAYA FINALIZADO, SE PODRÁN ACTUALIZAR LAS FECHAS/PUNTOS DEL PARTIDO + AÑADIR POSIBLE GANADOR
		if(currentTournament.getState() != StateTournamentEnum.FINALIZADO)
		{
			for (MatchModifyTournamentDTO currentModifiedMatch : tournamentDTO.getMatches())
			{
				//DECLARAMOS LAS VARIABLES NECESARIAS PARA LA ACTUALIZACIÓN
				boolean matchIsModified = false;
				Match currentMatch = matchS.getMatchById(currentModifiedMatch.getMatchId());
								
				Date dateNew = currentModifiedMatch.getMatchDate();
				Date dateOld  = currentMatch.getDate();
				
				//COMPROBAMOS SI SE HA MODIFICADO LA FECHA, EN CASO DE SER INFERIOR A LA FECHA ACTUAL, NO SE CAMBIARÁ
				if ((dateNew == null && dateOld != null) ||
					    (dateNew != null && dateOld == null) ||
					    (dateNew != null && dateOld != null && dateOld.compareTo(dateNew) != 0)) {
					    currentMatch.setDate(dateNew);
					    matchIsModified = true;
					    System.err.println("Editamos fecha: " + currentMatch.getDate());
					}			
				
				Date matchDate = currentMatch.getDate();
				
				//EN CASO DE QUE HAYA PASADO YA EL PARTIDO, MODIFICARÁ LOS PUNTOS DE LOS EQUIPOS + GANADOR
				if (matchDate != null) {
				    LocalDateTime matchDateTime = matchDate.toInstant()
				        .atZone(ZoneId.systemDefault())
				        .toLocalDateTime();

				    // Solo permitir editar puntos si matchDateTime <= ahora
				    if (!matchDateTime.isAfter(LocalDateTime.now())) {
				    	//SE COMPRUEBA SI SE HAN MODIFICADO LOS PUNTOS DE ALGUNOS DE LOS EQUIPOS Y QUE LOS PUNTOS NO SEAN IGUALES (QUE NO SEA EMPATE)
						if( ( currentModifiedMatch.getScoreTeam1() != currentMatch.getPointsTeam1() ||
							currentModifiedMatch.getScoreTeam2() != currentMatch.getPointsTeam2() ) && 
							currentModifiedMatch.getScoreTeam1() != currentModifiedMatch.getScoreTeam2() )
						{
							//SI ALGUNO DE LOS DOS ES MAYOR A 0, SE DECIDIRÁ AUTOMÁTICAMENTE EL GANADOR
							if(currentModifiedMatch.getScoreTeam1() > 0 || 
								currentModifiedMatch.getScoreTeam2() > 0)
							{	
								currentMatch.setPointsTeam1(currentModifiedMatch.getScoreTeam1());
								currentMatch.setPointsTeam2(currentModifiedMatch.getScoreTeam2());
					            System.err.println("Editamos puntaciones" + currentMatch.getPointsTeam1() + " - " + currentMatch.getPointsTeam2());

								if(currentModifiedMatch.getScoreTeam1() > currentModifiedMatch.getScoreTeam2()) {
									currentMatch.setWinnerTeam(currentMatch.getTeam1());
								} else {
									currentMatch.setWinnerTeam(currentMatch.getTeam2());
								}
					            System.err.println("Ganador" + currentMatch.getWinnerTeam());

								matchIsModified = true;
							}
						}
				    }
				}
				
				//EN CASO DE HABER MODIFICACIONES, SE ACTUALIZARÁ EL PARTIDO
				if(matchIsModified){
					currentMatch = matchS.updateMatch(currentMatch);
					if (currentTournament.getState().equals(StateTournamentEnum.PENDIENTE)) {
						currentTournament.setState(StateTournamentEnum.PROCESO);
						currentTournament = tournamentS.updateTournament(currentTournament);
					} else if(currentTournament.getState().equals(StateTournamentEnum.PROCESO)){
						
						if(currentMatch.getWinnerTeam() != null && currentMatch.getRound() == RoundMatchEnum.FINAL){
							currentTournament.setState(StateTournamentEnum.FINALIZADO);
							currentTournament = tournamentS.updateTournament(currentTournament);
						}
						
					}
		            System.err.println("Actualizando match" + currentMatch.toString());
				}
				
				
				//PREPARAMOS UN LISTADO NUEVO DE PARTIDOS EN LOS QUE HAYAN FINALIZADO Y TENGAN GANADOR
				if(currentMatch.getWinnerTeam() != null)
				{
					matchesWinnersEnded.add(currentMatch);
		            System.err.println("Agrego match finalizado" + currentMatch.toString());
				}
			}
		}
		
		createNewMatchTournament(matchesWinnersEnded, currentTournament);
		return ResponseEntity.status(HttpStatus.OK).body(currentTournament);
	}
	
	
	private void createNewMatchTournament(List<Match> matchesWinnersEnded, Tournament currentTournament)
	{
		int contTeamPerMatch = 0;
		Match newMatchNextRound = new Match(null,null,null,currentTournament,null,null,0,0,null);
		
		List<Match> auxMatchesWinnerEnded = new ArrayList<>(matchesWinnersEnded);
		
		for (Match currentMatch : matchesWinnersEnded)
		{
			Team currentWinnerTeam = currentMatch.getWinnerTeam();
			//TODO COMPROBAR EN CASO DE QUE SOLO HAYA UNA FINAL
			
			//PILLAMOS EL ROUND ACTUAL Y LO CAMBIAREMOS AL QUE SEA CORRESPONDIENTE
			RoundMatchEnum nextRoundMatch = currentMatch.getRound();
			
			if(nextRoundMatch == RoundMatchEnum.OCTAVOS)
				nextRoundMatch = RoundMatchEnum.CUARTOS_FINAL;
			
			else if(nextRoundMatch == RoundMatchEnum.CUARTOS_FINAL)
				nextRoundMatch = RoundMatchEnum.SEMIFINAL;
			
			else if(nextRoundMatch == RoundMatchEnum.SEMIFINAL)
				nextRoundMatch = RoundMatchEnum.FINAL;
			
			//DEVUELVE NULO SI NO EXISTE ESE PARTIDO CON EL ID DEL EQUIPO Y LA RONDA SELECCIONADA
			System.out.println("id del equipo ganador->"+currentWinnerTeam.getId());
			System.out.println("id del torneo->"+currentTournament.getId());
			System.out.println("ronda->"+nextRoundMatch);
			
			int isValid = matchS.countMatchByTeamIdTournamentIdAndStateRound(currentWinnerTeam.getId(),currentTournament.getId(),nextRoundMatch.name());
			
			if(isValid == 0)
			{
				if (contTeamPerMatch == 1) 
				{
					if(nextRoundMatch == newMatchNextRound.getRound())
					{
						newMatchNextRound.setTeam2(currentWinnerTeam);
						matchS.saveMatch(newMatchNextRound);
						
						newMatchNextRound = new Match(null,null,null,currentTournament,null,null,0,0,null);
						contTeamPerMatch = 0;
						auxMatchesWinnerEnded.remove(currentMatch);
						
						//TODO RELANZAR EL BUCLE DE MATCHESWINNERSENDED
						createNewMatchTournament(auxMatchesWinnerEnded, currentTournament);
						break;
						//TODO SI EL ESTADO DEL TORNEO PENDIENTE, SE PASA A EN PROCESO CUANDO SE JUEGUE EL PRIMER PARTIDO
						//TODO SI EL ESTADO DEL TORNEO PROCESO Y SE HACE EL PARTIDO FINAL, SE PASA A FINALIZADO
					}
				} else if(contTeamPerMatch == 0)
				{
					newMatchNextRound.setTeam1(currentWinnerTeam);
					newMatchNextRound.setRound(nextRoundMatch);
					
					auxMatchesWinnerEnded.remove(currentMatch);
					
					contTeamPerMatch++;
				}
			}
		}
		
		auxMatchesWinnerEnded.clear();
	}
}
