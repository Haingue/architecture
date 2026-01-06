import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
} from '@nestjs/common';
import { SuppliersService } from './suppliers.service';
import { SupplierDto } from './dto/supplier.dto';

@Controller('service/suppliers')
export class ServiceSuppliersController {
  constructor(private readonly suppliersService: SuppliersService) {}

  @Post()
  create(@Body() createSupplierDto: SupplierDto) {
    return this.suppliersService.create(createSupplierDto);
  }

  @Get()
  findAll() {
    return this.suppliersService.findAll();
  }

  @Get(':code')
  findOne(@Param('code') code: string) {
    return this.suppliersService.findOne(code);
  }

  @Patch(':code')
  update(@Param('code') code: string, @Body() updateSupplierDto: SupplierDto) {
    return this.suppliersService.update(code, updateSupplierDto);
  }

  @Delete(':code')
  remove(@Param('code') code: string) {
    return this.suppliersService.remove(code);
  }
}
