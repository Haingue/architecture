import { Module } from '@nestjs/common';
import { SuppliersService } from './suppliers.service';
import { ClientSuppliersController } from './page/client-suppliers.controller';
import { ServiceSuppliersController } from './service-suppliers.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Supplier } from './entities/supplier.entity';
import { InitSuppliersService } from './init-suppliers.service';

@Module({
  imports: [TypeOrmModule.forFeature([Supplier])],
  controllers: [ClientSuppliersController, ServiceSuppliersController],
  providers: [SuppliersService, InitSuppliersService],
  exports: [],
})
export class SuppliersModule {}
