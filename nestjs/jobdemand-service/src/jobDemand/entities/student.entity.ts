import { Column, Entity, PrimaryColumn } from 'typeorm';

@Entity()
export class Student {
  @PrimaryColumn()
  email: string;
  @Column()
  firstname: string;
  @Column()
  lastname: string;
  @Column()
  studentNumber: string;
  @Column({ type: 'timestamp' })
  lastLoginTimestamp: Date;
}
