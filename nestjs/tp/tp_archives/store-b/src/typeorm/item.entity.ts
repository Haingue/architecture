import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Item {

    @PrimaryGeneratedColumn()
    id: bigint;
    @Column({ nullable: false })
    name: string;
    @Column()
    weight: number;
    @Column()
    price: number;

}
