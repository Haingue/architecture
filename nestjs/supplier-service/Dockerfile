# Builder
FROM node:18-buster as builder
WORKDIR /app
RUN npm install -g @nestjs/cli
COPY package*.json /app/
RUN npm ci
COPY public /app/public
COPY src /app/src
COPY views /app/views
COPY test /app/test
COPY nest-cli.json tsconfig*.json /app/
COPY .eslintrc.js .prettierrc /app/
RUN npm run build

# ====================
# Runner
FROM node:18-slim
## Set environnment variable
ARG NODE_ENV=production
ENV NODE_ENV=$NODE_ENV
ARG ENV_NAME=Default
ENV ENV_NAME=$ENV_NAME
ARG SERVER_PORT=3000
ENV SERVER_PORT=$SERVER_PORT
ARG DB_HOST=host.docker.internal
ENV DB_HOST=$DB_HOST
ARG DB_PORT=5432
ENV DB_PORT=$DB_PORT
ARG DB_USERNAME=postgresql
ENV DB_USERNAME=$DB_USERNAME
ARG DB_PASSWORD=
ENV DB_PASSWORD=$DB_PASSWORD
ARG DB_NAME=SupplierService
ENV DB_NAME=$DB_NAME
WORKDIR /app
EXPOSE $SERVER_PORT
COPY --from=builder /app/dist /app/dist
COPY --from=builder /app/views /app/views
COPY --from=builder /app/public /app/public
COPY --from=builder /app/node_modules /app/node_modules
ENTRYPOINT [ "sh", "-c", "node /app/dist/main.js" ]