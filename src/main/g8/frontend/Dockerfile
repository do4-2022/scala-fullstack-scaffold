FROM sbtscala/scala-sbt:eclipse-temurin-jammy-19.0.1_10_1.9.0_3.3.0 as build

# Install nodejs
RUN curl -OL https://nodejs.org/dist/v18.16.0/node-v18.16.0-linux-x64.tar.xz

RUN apt-get update && apt-get install -y xz-utils
RUN tar -xf node-v18.16.0-linux-x64.tar.xz
RUN mv node-v18.16.0-linux-x64 /opt/node

ENV PATH="/opt/node/bin:\${PATH}"


WORKDIR /app

# TODO: change the API URL 
ENV VITE_API_URL http://localhost:8080
COPY . . 
RUN npm ci
RUN npm run build

FROM nginx:1.25
WORKDIR /app
COPY --from=build /app/dist /usr/share/nginx/html

# will be exposed on port 80