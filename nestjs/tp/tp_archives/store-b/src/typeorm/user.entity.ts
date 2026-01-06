import { Column, Entity, PrimaryColumn } from 'typeorm';

@Entity()
export class User {

  @PrimaryColumn()
  username: string;

  @Column({
    name: 'email_address',
    nullable: false,
    default: '',
  })
  email: string;

  @Column({
    nullable: false,
    default: '',
  })
  password: string;
}
