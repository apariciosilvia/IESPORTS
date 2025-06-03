import type { MatchModifyTournamentDTO } from '@/model/dto/matchModifyTournamentDTO';

export interface TournamentModifyDTO {
  tournamentId: number;
  tournamentNameModified: string;
  sportModifiedId: number;
  matches: MatchModifyTournamentDTO[];
}	