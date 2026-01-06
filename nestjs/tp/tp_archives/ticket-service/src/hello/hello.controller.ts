import { Controller, Get, Logger } from '@nestjs/common';
import { HelloService } from './hello.service';

@Controller()
export class HelloController {
  private readonly logger = new Logger(HelloController.name); // Use default logger

  constructor(private readonly helloService: HelloService) {}

  @Get()
  getHello(): string {
    this.logger.log('Load hello world');
    return this.helloService.getHello();
  }
}
