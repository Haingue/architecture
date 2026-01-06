import { BadRequestException } from '@nestjs/common';
import { Test } from '@nestjs/testing';
import { HelloController } from './hello.controller';
import { HelloService } from './hello.service';

describe('HelloController', () => {
  let helloController: HelloController;

  beforeEach(async () => {
    const moduleRef = await Test.createTestingModule({
      controllers: [HelloController],
      providers: [HelloService]
    }).compile();

    helloController = moduleRef.get<HelloController>(HelloController);
  });

  describe('hello', () => {
    it('should return "Hello World!"', () => {
      expect(helloController.getHello()).toBe('Hello World !');
    });
  });

  describe('hello name', () => {
    it('should return "Hello World!" for a specific name', () => {
      expect(helloController.getHelloByName('test')).toBe('Hello World test !');
      try {
          helloController.getHelloByName(null)
      } catch (error) {
        expect(error).toBeInstanceOf(BadRequestException)
      }
    });
  });
});
