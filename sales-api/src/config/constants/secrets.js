const env = process.env

export const MONGO_DB_URL = env.MONGO_DB_URL 
    ? env.MONGO_DB_URL 
    : "mongodb://admin:123456@localhost:27017";

export const API_SECRET = env.API_SECRET 
    ? env.API_SECRET 
    : "YXV0aC1hcGktc2VjcmV0LWRldi0xMjM0NTY=";


export const RABBIT_MQ_URL = env.RABBIT_MQ_URL 
    ? env.RABBIT_MQ_URL 
    : "amqps://krwrcvac:PP7JlV5YL6_Z2XC9HXRzy-fbWk6XHdnj@chimpanzee.rmq.cloudamqp.com/krwrcvac";

export const PRODUCT_API_URL = env.PRODUCT_API_URL 
    ? env.PRODUCT_API_URL
    : "http://localhost:8081/api/product";