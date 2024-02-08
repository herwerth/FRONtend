FROM node:16 AS node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

FROM nginx:latest
COPY --from=node /app/dist/frontend /usr/share/nginx/html
EXPOSE 4200
