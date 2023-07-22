FROM node:18-buster as builder
WORKDIR /app
COPY package.json package-lock.json /app/
RUN npm ci
COPY public src views /app/
COPY nest-cli.json tsconfig.json tsconfig.build.json /app/
COPY [".env", ".eslintrc.js", ".prettierrc", "/app/"]
# COPY . .
RUN npm run build

FROM node:18-slim
WORKDIR /app
EXPOSE 9090
COPY --from=builder /app/dist /app
ENTRYPOINT [ "sh", "-c", "node dist/main.js" ]