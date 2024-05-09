**KarakTea**:
> This class represents the flyweight object that will be cached. In this example, it's a simple class with no methods
> or fields.

**TeaMaker**:
> This class acts as a factory to create tea objects. It saves the tea objects in a HashMap for caching.

**TeaShop**:
> This class takes orders and serves tea to tables. It uses the TeaMaker to create tea objects based on the order types.