import { JobDemand } from 'src/jobDemand/entities/jobDemand.entity';
import { Student } from '../jobDemand/entities/student.entity';
import { Speciality } from 'src/jobDemand/entities/speciality.entity';
const entities = [Student, Speciality, JobDemand];

export { Student };
export { Speciality };
export { JobDemand };
export default entities;
