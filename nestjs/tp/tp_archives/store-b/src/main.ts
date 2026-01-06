import { ConfigService } from '@nestjs/config';
import { NestFactory } from '@nestjs/core';
import { NestExpressApplication } from '@nestjs/platform-express';
import { join } from 'path';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create<NestExpressApplication>(AppModule, {
    logger: ['debug', 'log', 'warn', 'error']
  });
  const config: ConfigService = app.get(ConfigService);
  const port: number = config.get<number>('SERVER_PORT');

  // app.useGlobalPipes(new ValidationPipe({ whitelist: true, transform: true }));
  app.useStaticAssets(join(__dirname, '..', 'public'));
  app.setBaseViewsDir(join(__dirname, '..', 'views'));
  app.setViewEngine('hbs');

  await app.listen(port, () => {
    console.log('[ENV]', process.env.NODE_ENV, config.get<string>('ENV_NAME'))
    console.log('[Web]', config.get<string>('SERVER_PORT'))
    console.log('[Database]', `${config.get<string>('DB_HOST')}:${config.get<string>('DB_PORT')}/${config.get<string>('DB_NAME')}`)
  });

}
bootstrap();
