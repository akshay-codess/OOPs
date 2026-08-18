# Strategy

**Type:** Behavioral design pattern

## Intent

Strategy turns a set of behaviors into objects and makes them interchangeable
inside an original context object.

The original object, called the **context**, holds a reference to a
**strategy** object. The context delegates executing the behavior to the
linked strategy object. To change the way the context performs its work,
other objects may replace the currently linked strategy object with another
one.

## Structure in this example

| File | Role |
|---|---|
| `PayStrategy.java` | The **Strategy** interface — declares `pay(int amount)`, the one method every payment method must implement |
| `PayByPayPal.java` | **Concrete strategy** #1 |
| `PayByCreditCard.java` | **Concrete strategy** #2 |
| `Order.java` | The **Context** — holds a `PayStrategy` reference and delegates `checkout()` to it |
| `Client.java` | Demo `main` — builds one `Order`, swaps strategies at runtime |

`Order` never contains payment logic itself. It just stores a `payStrategy`
field and calls `payStrategy.pay(totalCost)` — it doesn't know or care *how*
the payment happens. Neither concrete strategy knows the other exists.

`Order.setPayStrategy(...)` is the "setter that lets other objects replace
the nested object" — calling it twice in `Client` with different strategies
changes `Order`'s behavior with zero changes to `Order`'s code:

```java
Order order = new Order();
order.addCost(2200);
order.addCost(1850);

order.setPayStrategy(new PayByPayPal("amanda@ya.com"));
order.checkout();

order.setPayStrategy(new PayByCreditCard("4111-1111-1111-1111"));
order.checkout();
```

## How to identify it

Look for two things in code to spot Strategy:

1. A class that delegates real work to a field typed as an interface
   (`Order` → `PayStrategy`).
2. A way to swap that field's value from outside (`setPayStrategy`).

## When to use it

Strategy earns its keep when **the algorithm needs to change at runtime**,
driven by user choice, config, or context — like a customer picking PayPal
vs. credit card mid-checkout.

If you only ever construct the strategy once and never swap it, you
probably don't need the pattern — a plain method or, in Java 8+, a lambda
(functional interfaces) is simpler alternative.

## Real examples in the JDK

- `java.util.Comparator#compare()` passed into `Collections.sort(list, comparator)`
  — `Collections.sort` is the context, `Comparator` is the strategy.
- `javax.servlet.http.HttpServlet#service()` dispatching to `doGet`/`doPost`
  — the servlet container is the context, your servlet subclass supplies the
  strategy.
- `javax.servlet.Filter#doFilter()` in a filter chain — same idea, chained.

Java 8's lambda functions are often a simpler alternative to writing a full
Strategy class hierarchy for cases with a single abstract method.
