let products = [{
    name: "Product 1",
    actualPrice: 1000,
    salePrice: 1000,
    couponApplied: false,
},
{
    name: "Product 2",
    actualPrice: 2000,
    salePrice: 1900,
    couponApplied: true,
},
{
    name: "Product 3",
    actualPrice: 2000,
    salePrice: 2000,
    couponApplied: false,
},
{
    name: "Product 4",
    actualPrice: 2000,
    salePrice: 2000,
    couponApplied: false,
},
];

const coupon = [{
    code: "p10",
    discountPrice: 100,
    applicableProducts: ["Product 1", "Product 2"],
},
{
    code: "p20",
    discountPrice: 200,
    applicableProducts: ["Product 3", "Product 4"],
},
];

const applyCouponToProduct = (product, couponCode) => {
const couponToApply = coupon.find((c) => c.code === couponCode);

if (!couponToApply) return "Invalid Coupon Code";

if (couponToApply.applicableProducts.includes(product.name)) {
    if (product.couponApplied) return "Coupon already applied to this product";
    else {
        product.salePrice = product.salePrice - couponToApply.discountPrice;
        product.couponApplied = true;
        products = products.filter((p) => p.name !== product.name);
        products.push(product);

        return product;
    }
} else {
    return "Coupon not applicable to this product";
}
};

console.log(applyCouponToProduct(products[0], "p10"));
// console.log("products list", products);