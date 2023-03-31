import Order from "../../modules/sales/model/Order.js";

export async function createInitialData() {
    let existing = await Order.find();
if (existing && existing.length > 0) {
    console.info("Removing existing data...");
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
            }
        ],
        user: {
            id: "asnidvjijcsd",
            name: "User Test",
            email:"usertest@gmail.com",
        },
        status: "APPROVED",
        createdAt: new Date(),
        updatedAt: new Date(),
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
            }
        ],
        user: {
            id: "aahbdhjbhvv",
            name: "User Test 2",
            email:"usertest2@gmail.com"
        },
        status: "REJECTED",
        createdAt: new Date(),
        updatedAt: new Date()
    });

    let initialData = await Order.find();
    console.info(`Initial data was created: ${JSON.stringify(initialData, undefined, 4)}`);


}