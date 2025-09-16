import { Column, Entity, PrimaryColumn } from 'typeorm/browser';

export type SpecialityCategory =
  | 'IT'
  | 'Finance'
  | 'Marketing'
  | 'HR'
  | 'Sales';

@Entity()
export class Speciality {
  @PrimaryColumn()
  name: string;
  @Column()
  category: SpecialityCategory;
}
