import type { Course } from '@/model/course';
import axios from 'axios';

function getCourses(): Promise<Course[]> {

    return new Promise((resolve, reject) => {
  
      const url = `${import.meta.env.VITE_URL_API}/course/getCourses`;
  
      axios.get(url)
        .then(response => {
          resolve(response.data); 
        })
        .catch(error => {
          reject(error);
        });
    });
};

export { getCourses };
