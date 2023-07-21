import { Test, TestingModule } from '@nestjs/testing';
import { INestApplication } from '@nestjs/common';
import * as request from 'supertest';
import { AppModule } from './../src/app.module';
import { Item } from '../src/typeorm';
import assert from 'assert';

describe('AppController (e2e)', () => {
  let app: INestApplication;
  let jwt: string;

  beforeEach(async () => {
    const moduleFixture: TestingModule = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    app = moduleFixture.createNestApplication();
    await app.init();
  });

  describe('First test', () => {
    it('JWT test', () => {
      return request(app.getHttpServer())
      .post('/login')
      .send({
        username: "admin",
        password: "azertyazerty"
      })
      //.expect(200)
      .then((response) => {
        jwt = response.body.access_token;
        expect(response.status).toBe(200);
        expect(jwt).toBeDefined();
      })
    })
    it('/ (GET)', () => {
      return request(app.getHttpServer())
        .get('/hello')
        .expect(200)
        .expect('Hello World !');
    });
  })

  describe('Item', () => {
    it('/service/item (GET)', () => {
      return request(app.getHttpServer())
      .get('/service/item')
      .set('Authorization', jwt)
        .expect(200);
    });
    it('/service/item (POST)', () => {
      const item = {
        name: 'Test item 0',
        price: 10.0,
        weight: 0.5
      };
      return request(app.getHttpServer())
        .post('/service/item')
        .set('Authorization', jwt)
        .send(item)
        .expect(201)
        .expect(res => {
          const savedItem:Item = res.body;
          assert(savedItem.id !== null && savedItem.id > 0);
          assert(savedItem.name == item.name);
          assert(savedItem.price == item.price);
          assert(savedItem.weight == item.weight);
        })
    });
  })

});
function done() {
  throw new Error('Function not implemented.');
}

