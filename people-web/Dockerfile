FROM node:13

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 8888

CMD ["npm", "run", "serve"]