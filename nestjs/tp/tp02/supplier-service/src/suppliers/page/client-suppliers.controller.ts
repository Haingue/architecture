import {
  Controller,
  Get,
  Render,
  Inject,
  Res,
  Param,
  Req,
  Redirect,
  Post,
  Body,
  Logger,
} from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { SuppliersService } from '../suppliers.service';
import { Request, Response } from 'express';
import { SupplierDto } from '../dto/supplier.dto';

@Controller('client/supplier')
export class ClientSuppliersController {
  @Inject(ConfigService)
  private readonly configService: ConfigService;
  @Inject(SuppliersService)
  private readonly supplierService: SuppliersService;

  @Get()
  @Render('supplier-list')
  async getSupplierPage(@Req() request: Request): Promise<any> {
    return {
      suppliers: await this.supplierService.findAll(),
      error: request.header['error'],
      systemError: null,
      date: new Date().toISOString(),
      envName: this.configService.get<string>('ENV_NAME'),
      port: this.configService.get<string>('SERVER_PORT'),
    };
  }

  @Get(':code')
  async deleteSupplier(
    @Param('code') code: string,
    @Req() request: Request,
    @Res() response: Response,
  ): Promise<any> {
    try {
      await this.supplierService.remove(code);
    } catch (error) {
      request.header['error'] = error;
    }
    return response.redirect('/client/supplier');
  }

  @Post()
  @Redirect('/client/supplier')
  async postSupplier(
    @Body() supplierDto: SupplierDto,
    @Req() request: Request,
  ): Promise<any> {
    Logger.log('Post new supplier');
    try {
      await this.supplierService.create(supplierDto);
    } catch (error) {
      request.header['error'] = error;
    }
    return;
  }
}
