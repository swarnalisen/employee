FROM node:16.15.0-alpine3.15 AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.20.2-alpine
COPY ./config/nginx.conf /etc/nginx/nginx.conf
COPY --from=build-stage /app/dist/angular8-springboot-client /var/www/demo
EXPOSE 4200
CMD ["nginx","-g","daemon off;"]