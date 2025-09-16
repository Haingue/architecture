import { Column, Entity, ManyToOne, OneToOne, PrimaryColumn } from 'typeorm';
import { Speciality } from './speciality.entity';
import { Student } from './student.entity';

@Entity()
export class JobDemand {
  @PrimaryColumn()
  id: string;
  @Column()
  title: string;
  @Column()
  @OneToOne(() => Student)
  requestor: Student;
  @Column()
  startDate: Date;
  @Column()
  endDate: Date;
  @Column({ type: 'time' })
  startDayTime: Date;
  @Column()
  @ManyToOne(() => Speciality)
  speciality: Speciality;
  @Column({ type: 'time' })
  endDayTime: Date;
  @Column({ type: 'timestamp' })
  creationDatetime: Date;
  expirationDays: number;
  @Column({ type: 'timestamp' })
  creationTimestamp: Date;
}
