import express from 'express';

import { connectMongoDb } from "./src/config/db/mongoDbConfig.js";
import { createInitialData } from "./src/config/db/initialData.js";
import { connectRabbitMq } from './src/config/rabbitmq/rabbitConfig.js';

import CheckToken from './src/config/auth/CheckToken.js';

import { sendMessageToProductStockUpdateQueue } from './src/modules/product/rabbitmq/productStockUpdateSender.js';

const app = express();
const env = process.env;
const PORT = env.PORT || 8082;

connectMongoDb();
createInitialData();
connectRabbitMq();

//app.use(CheckToken);

app.get("/teste", (req, res) => {
    try {
        sendMessageToProductStockUpdateQueue([
            {
                productId: 100,
                quantity: 2,
            },
            {
                productId: 200,
                quantity: 3,
            },
            {
                productId: 300,
                quantity: 5,
            },
        ]);
        return res.status(200).json({ status: 200 });
    }
    catch (err) {
        console.log(err);
        return res.status(500).json({ error: true });
    }
});

app.get('/api/status',  async (req, res) => {
    
    return res.status(200).json({
        sevice: "Sales-API",
        status: "up",
        httpStatus: 200
    })
})

app.listen(PORT, () => {
    console.info(`Server started sucessfully at port ${PORT}`);
})