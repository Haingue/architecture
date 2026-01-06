import { Test, TestingModule } from '@nestjs/testing';
import { ServiceSuppliersController } from './service-suppliers.controller';
import { SuppliersService } from './suppliers.service';

describe('SuppliersController', () => {
  let controller: ServiceSuppliersController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [ServiceSuppliersController],
      providers: [SuppliersService],
    }).compile();

    controller = module.get<ServiceSuppliersController>(
      ServiceSuppliersController,
    );
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
