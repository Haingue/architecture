import { Entity, PrimaryColumn, Column } from 'typeorm';

@Entity()
export class Supplier {
  @PrimaryColumn()
  code: string;

  @Column()
  name: string;

  @Column()
  address: string;
}
