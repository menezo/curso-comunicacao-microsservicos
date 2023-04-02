import express from 'express';

import { connectMongoDb } from "./src/config/db/mongoDbConfig.js";
import { createInitialData } from "./src/config/db/initialData.js";
import { connectRabbitMq } from './src/config/rabbitmq/rabbitConfig.js';

import CheckToken from './src/config/auth/CheckToken.js';
import orderRoutes from "./src/modules/sales/routes/OrderRoutes.js";

const app = express();
const env = process.env;
const PORT = env.PORT || 8082;

connectMongoDb();
createInitialData();
connectRabbitMq();

app.use(express.json());
app.use(CheckToken);
app.use(orderRoutes);

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