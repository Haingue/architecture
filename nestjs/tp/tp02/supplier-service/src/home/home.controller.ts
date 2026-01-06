import { Controller, Get, Inject, Render } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';

@Controller()
export class HomeController {
  @Inject(ConfigService)
  private readonly configService: ConfigService;

  @Get()
  @Render('index')
  getHello(): any {
    return {
      date: new Date().toISOString(),
      envName: this.configService.get<string>('ENV_NAME'),
      port: this.configService.get<string>('SERVER_PORT'),
    };
  }
}
