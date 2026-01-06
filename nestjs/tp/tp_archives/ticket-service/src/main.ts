import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { NestExpressApplication } from '@nestjs/platform-express';
import { join } from 'path';
import { ConfigService } from '@nestjs/config';
import * as hbs from 'hbs';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';

async function bootstrap() {
  // Create Nestjs app
  const app = await NestFactory.create<NestExpressApplication>(AppModule, {
    logger: ['debug', 'log', 'warn', 'error'],
  });
  // Enable configuration from .env files
  const config: ConfigService = app.get(ConfigService);
  // Define server port from .env file
  const port: number = config.get<number>('SERVER_PORT');

  // Setup HDS
  app.setViewEngine('hbs');
  hbs.registerPartials(join(__dirname, '..', 'views/partials'));
  app.setBaseViewsDir(join(__dirname, '..', 'views'));
  app.useStaticAssets(join(__dirname, '..', 'public'));
  // Setup CORS policy
  app.enableCors({
    origin: true,
    methods: 'GET,HEAD,PUT,PATCH,POST,DELETE,OPTIONS',
    credentials: true,
  });
  // Enable OpenAPI
  const openApi = new DocumentBuilder()
    .setTitle('Ticket Service')
    .setDescription('The ticket API description')
    .setVersion('1.0')
    .addTag('ticket-service')
    .build();
  // Enable OpenAPI & Swagger
  const document = SwaggerModule.createDocument(app, openApi);
  SwaggerModule.setup('api', app, document);

  // Start Nestjs server
  await app.listen(port, () => {
    console.log('[ENV]', process.env.NODE_ENV, config.get<string>('ENV_NAME'));
    console.log('[Web]', config.get<string>('SERVER_PORT'));
    console.log(
      `[Database] ${config.get<string>('DB_HOST')}:${config.get<string>('DB_PORT')}/${config.get<string>('DB_NAME')}`,
    );
  });
}
bootstrap();
