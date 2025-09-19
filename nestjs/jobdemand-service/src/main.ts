import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  app.enableCors();

  /* OpenAPI & Swagger UI */
  const config = new DocumentBuilder()
    .setTitle('JobDemand API')
    .setDescription('The job demand API description')
    .setVersion('1.0')
    .addTag('jobdemand')
    .build();
  const documentFactory = () => SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('api', app, documentFactory);

  await app.listen(process.env.PORT ?? 3000);
}
bootstrap();
