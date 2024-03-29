import Order from "../../modules/sales/model/Order.js";
import { v4 as uuidv4 } from "uuid";

export async function createInitialData() {

    try {
        let existingData = await Order.find();
        if (existingData && existingData.length > 0) {
            console.info("Remove existing data...");
            await Order.collection.drop();
        }
        await Order.create({
            products: [
                {
                    productId: 100,
                    quantity: 3,
                },
                {
                    productId: 200,
                    quantity: 2,
                },
                {
                    productId: 300,
                    quantity: 1,
                },
            ],
            user: {
                id: "asnidvjijcsd",
                name: "User Test",
                email:"usertest@gmail.com",
            },
            status: "APPROVED",
            createdAt: new Date(),
            updatedAt: new Date(),
            transactionid: uuidv4(),
            serviceid: uuidv4(),
        });
        await Order.create({
            products: [
                {
                    productId: 200,
                    quantity: 2,
                },
                {
                    productId: 300,
                    quantity: 19,
                },
            ],
            user: {
                id: "aahbdhjbhvv",
                name: "User Test 2",
                email:"usertest2@gmail.com"
            },
            status: "REJECTED",
            createdAt: new Date(),
            updatedAt: new Date(),
            transactionid: uuidv4(),
            serviceid: uuidv4()
        });
        let initialData = await Order.find();
        console.info(
            `Initial data was created: ${JSON.stringify(initialData, undefined, 4)}`
        );
    }
    catch (error) {
        console.error(error);
    }
}