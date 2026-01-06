import { Column, Entity, JoinColumn, ManyToOne, OneToOne, PrimaryColumn } from 'typeorm';
import { Speciality } from './speciality.entity';
import { Student } from './student.entity';

@Entity()
export class JobDemand {
  @PrimaryColumn()
  id: string;
  @ManyToOne(() => Speciality, (speciality) => speciality.name)
  speciality: Speciality;
  @ManyToOne(() => Student, (student) => student.email)
  requestor: Student;
  @Column()
  title: string;
  @Column()
  startDate: Date;
  @Column()
  endDate: Date;
  @Column({ type: 'time' })
  startDayTime: Date;
  @Column({ type: 'time' })
  endDayTime: Date;
  @Column({ type: 'timestamp' })
  creationDatetime: Date;
  @Column()
  expirationDays: number;
  @Column({ type: 'timestamp' })
  creationTimestamp: Date;
}
