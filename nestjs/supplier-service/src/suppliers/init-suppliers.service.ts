import { Injectable, Logger, OnModuleInit } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Supplier } from './entities/supplier.entity';
import { Repository } from 'typeorm';

@Injectable()
export class InitSuppliersService implements OnModuleInit {
  private readonly logger = new Logger(InitSuppliersService.name);
  @InjectRepository(Supplier) // TODO try outside the contructor
  private readonly repository: Repository<Supplier>;

  async onModuleInit(): Promise<void> {
    this.logger.log('Initialize supplier data');
    const s1 = new Supplier();
    s1.code = '10780';
    s1.name = 'Door factory';
    s1.address = '1 rue des oliviers 59000';
    this.repository.save(s1);

    const s2 = new Supplier();
    s2.code = '55190';
    s2.name = 'Panel factory';
    s2.address = '42 rue Saint-Charles 59000';
    this.repository.save(s2);
  }
}
