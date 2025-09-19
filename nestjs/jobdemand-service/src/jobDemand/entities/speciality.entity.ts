import { Column, Entity, PrimaryColumn } from 'typeorm';

export enum SpecialityCategory {
  IT = 'IT',
  Finance = 'Finance',
  Marketing = 'Marketing',
  HR = 'HR',
  Sales = 'Sales',
}

@Entity()
export class Speciality {
  @PrimaryColumn()
  name: string;
  @Column({
    type: 'enum',
    enum: SpecialityCategory,
  })
  category: SpecialityCategory;
}
