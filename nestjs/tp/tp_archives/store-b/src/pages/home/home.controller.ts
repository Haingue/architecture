import { Controller, Get, Inject, Render } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { ItemService } from '../../item/item.service';

@Controller()
export class HomeController {
  
  @Inject()
  private readonly configService: ConfigService;
  @Inject()
  private readonly itemService: ItemService;

  @Get()
  @Render('home')
  async loadHomePage() {
    const environmentName = this.configService.get('environmentName');
    const port = this.configService.get('server.port');
    const items = await this.itemService.findAll();
    return { environmentName, port, items };
  }
}
