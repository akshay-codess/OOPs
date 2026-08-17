# LLD Exercise: Design a Pen

A collaborative low-level design walkthrough — requirements gathering, class design, and Java implementation, done interview-style (Q&A, self-correction, iterative review).

---

## Round 1: Requirements Gathering

**Scope decisions:**
- Modeling physical writing pens (Ball, Gel, Fountain) — not a UI tool, not a store/inventory system.
- Focused on the pen entity itself: state + behavior.
- Designed for extensibility — new pen types should be addable without rewriting existing code.

**Domain facts established (with corrections along the way):**
- Every pen has: `name`, `brand`, `price`, `penType`.
- **Ball / Gel pens**: contain a `Refill` as a fixed **attribute**. Refill is *not swappable* in this model (no refill-swap behavior).
- **Fountain pens**: do *not* have a `Refill` object. They hold `Ink` and `Nib` **directly**, and support a `refill(Ink)` **behavior** (you physically add ink — that's the real-world meaning of "refilling" a fountain pen).
- `Refill` = `Ink` + `Nib`.
- `Ink` has `color` and a list of `InkType` (e.g. `TRANSPARENT`, `WATERPROOF`).
- `Nib` has a `radius`.
- Pens can be **capped, uncapped, or have no cap at all** (e.g. click/retractable ball pens) — modeled as an enum `CapState { CAPPED, UNCAPPED, NOCAP }` rather than a boolean, since two states weren't enough to capture reality.

**Operations identified:**
- `write()` — should fail if the pen is `CAPPED`.
- `cap()` / `unCap()` — common to all pens, not type-specific.
- `refill(Ink)` — only for pens that support it (`FountainPen`).

**Key structural decision — inheritance vs. composition:**
- A `Pen` **is-a** `BallPen`/`GelPen`/`FountainPen` (inheritance for the type hierarchy).
- A `Pen` **has-a** `Refill` (composition for the refill relationship) — except `FountainPen`, which has-a `Ink` and has-a `Nib` directly.
- A capability that only *some* pen types support (refilling) was modeled as an **interface** (`RefillablePen`), not baked into the base class — so only `FountainPen` implements it.

---

## Round 2: Design Corrections Made Along the Way

These are the actual mistakes caught and fixed during the exercise — the most valuable part of the process:

1. **Contradiction resolved**: initially said "fountain pens have no refill" and "fountain pens have a refill" in the same breath. Resolved to: fountain pens don't have a `Refill` *object*, but do have a `refill()` *behavior*.
2. **`RefillablePen` was first assigned to the wrong classes** — initially put on `FountainPen` correctly, but `BallPen`/`GelPen` were ambiguously described as also having "refill behavior," when actually they only have a fixed `Refill` **attribute**, no swap behavior at all.
3. **Nib placement**: initially unclear whether `FountainPen`'s nib should live inside a `Refill`. Resolved: `FountainPen` holds `Nib` and `Ink` as **direct fields**, separate from the `Refill` class used by `BallPen`/`GelPen`.
4. **Cap state modeling**: started as a `boolean capped`, upgraded to a 3-value enum after recognizing click pens have no cap at all — a boolean couldn't represent that third state.
5. **`write()`/`cap()`/`unCap()` were initially marked `abstract`** — but since the capped-state logic is identical for every pen type, making them abstract would have forced every subclass to duplicate the same logic (and risk inconsistency). Moved to concrete implementations in the base `Pen` class.
6. **Illegal-state handling bug**: `cap()`/`unCap()` printed a warning message for illegal transitions (e.g. capping a `NOCAP` pen) but then **fell through and changed the state anyway** — no `return`/`throw` to stop execution. Fixed by throwing exceptions and stopping execution on invalid calls.
7. **Generic `RuntimeException` everywhere** — flagged as a real production concern (custom exception types like `PenCappedException` would be better), explicitly deferred as a "would do in production, skipping for interview time" trade-off rather than silently ignored.
8. **Method visibility**: `write()`/`cap()`/`unCap()` were originally package-private (no modifier) — fixed to `public` since external classes (like `PenFactory`) need to call them.
9. **Encapsulation gaps in `Ink`**:
    - Initially had no getters at all (write-only object).
    - Added setters out of habit — then removed them after recognizing `Ink` should be **immutable after construction** (a pen's ink identity shouldn't silently change post-creation).
    - `getInkTypes()` initially returned the live internal list — fixed to return a **defensive copy**, preventing external mutation of internal state.
    - Even after fixing the getter, the **constructor** was still storing the caller's list reference directly — meaning the caller could mutate `Ink`'s internals from outside *after* construction, bypassing the getter's protection entirely. Fixed by defensively copying in the constructor too.
10. **`Nib` was missing a getter** entirely (same write-only bug as `Ink`, caught during `Refill` review).
11. **`Refill` reasoning**: correctly did *not* defensively copy `Ink`/`Nib` references, since those are already immutable by design — the mutation risk that justified defensive copying for `List<InkType>` doesn't apply to references to already-immutable objects.
12. **`BallPen`/`GelPen` constructors initially accepted `penType` and `capState` as parameters but never used them** (hardcoded inside `super(...)` instead) — dead, misleading parameters removed. A `BallPen` should never need to be *told* it's a ball pen or that it starts capped; the subclass already knows.
13. **`RefillablePen.refill()` was missing its `Ink` parameter** — without it, `FountainPen` would have no way to know *what ink* to refill with.
14. **`FountainPen.refill()` was a compile error** at one point — implemented as `refill()` (no args) while the interface required `refill(Ink ink)`; `@Override` doesn't compile unless the signature matches exactly.
15. **`refill(Ink ink)` semantics**: settled on "replace the old ink entirely" (option a) as the physically sensible behavior — merging ink colors doesn't make real-world sense.

---

## Final Class Design

```
enum PenType { BALL, GEL, FOUNTAIN }
enum CapState { CAPPED, UNCAPPED, NOCAP }
enum InkType { TRANSPARENT, WATERPROOF }

abstract class Pen
  - name: String
  - brand: String
  - price: double
  - penType: PenType
  - capState: CapState
  + write()          // throws if CAPPED
  + cap()             // throws if already CAPPED or NOCAP
  + unCap()           // throws if already UNCAPPED or NOCAP

class Ink                          // immutable
  - color: String
  - inkTypes: List<InkType>        // defensively copied in and out
  + getColor(), getInkTypes()

class Nib                          // immutable
  - radius: double
  + getRadius()

class Refill
  - ink: Ink
  - nib: Nib
  + getInk(), getNib()

interface RefillablePen
  + refill(ink: Ink)

class BallPen extends Pen
  - refill: Refill
  + getRefill()

class GelPen extends Pen
  - refill: Refill
  + getRefill()

class FountainPen extends Pen implements RefillablePen
  - ink: Ink
  - nib: Nib
  + getInk(), getNib()
  + refill(ink: Ink)   // replaces this.ink

class PenRequest                   // bundles construction data for the factory
  - type, name, brand, price, refill, nib, ink

class PenFactory
  + static getPenForType(PenRequest request): Pen
```

---

## Final Java Code

### Pen.java
```java
package pen;

public abstract class Pen {
    private String name;
    private String brand;
    private PenType penType;
    private CapState capState;
    private double price;

    public Pen(String name, String brand, PenType penType, CapState capState, double price) {
        this.name = name;
        this.brand = brand;
        this.penType = penType;
        this.capState = capState;
        this.price = price;
    }

    public void write() {
        if (this.capState == CapState.CAPPED) {
            throw new RuntimeException("cannot write when cap on");
        } else {
            System.out.println("Wrote Something");
        }
    }

    public void cap() {
        if (this.capState == CapState.NOCAP) {
            throw new RuntimeException("Pen Has No Cap");
        }
        if (this.capState == CapState.CAPPED) {
            throw new RuntimeException("Already Capped");
        }
        this.capState = CapState.CAPPED;
    }

    public void unCap() {
        if (this.capState == CapState.NOCAP) {
            throw new RuntimeException("Pen Has No Cap");
        }
        if (this.capState == CapState.UNCAPPED) {
            throw new RuntimeException("Already Uncapped");
        }
        this.capState = CapState.UNCAPPED;
    }
}
```

### PenType.java / CapState.java / InkType.java
```java
package pen;

public enum PenType { GEL, BALL, FOUNTAIN }
```
```java
package pen;

public enum CapState { CAPPED, UNCAPPED, NOCAP }
```
```java
package pen;

public enum InkType { TRANSPARENT, WATERPROOF }
```

### Ink.java
```java
package pen;

import java.util.ArrayList;
import java.util.List;

public class Ink {
    private String color;
    private List<InkType> inkTypes;

    public Ink(String color, List<InkType> inkTypes) {
        this.color = color;
        this.inkTypes = new ArrayList<>(inkTypes);
    }

    public String getColor() {
        return color;
    }

    public List<InkType> getInkTypes() {
        return new ArrayList<>(inkTypes);
    }
}
```

### Nib.java
```java
package pen;

public class Nib {
    private double radius;

    public Nib(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
```

### Refill.java
```java
package pen;

public class Refill {
    private Ink ink;
    private Nib nib;

    public Refill(Ink ink, Nib nib) {
        this.ink = ink;
        this.nib = nib;
    }

    public Ink getInk() {
        return ink;
    }

    public Nib getNib() {
        return nib;
    }
}
```

### RefillablePen.java
```java
package pen;

public interface RefillablePen {
    void refill(Ink ink);
}
```

### BallPen.java
```java
package pen;

public class BallPen extends Pen {
    private Refill refill;

    public BallPen(String name, String brand, double price, Refill refill) {
        super(name, brand, PenType.BALL, CapState.CAPPED, price);
        this.refill = refill;
    }

    public Refill getRefill() {
        return refill;
    }
}
```

### GelPen.java
```java
package pen;

public class GelPen extends Pen {
    private Refill refill;

    public GelPen(String name, String brand, double price, Refill refill) {
        super(name, brand, PenType.GEL, CapState.CAPPED, price);
        this.refill = refill;
    }

    public Refill getRefill() {
        return refill;
    }
}
```

### FountainPen.java
```java
package pen;

public class FountainPen extends Pen implements RefillablePen {
    private Nib nib;
    private Ink ink;

    public FountainPen(String name, String brand, double price, Nib nib, Ink ink) {
        super(name, brand, PenType.FOUNTAIN, CapState.CAPPED, price);
        this.nib = nib;
        this.ink = ink;
    }

    public Nib getNib() {
        return nib;
    }

    public Ink getInk() {
        return ink;
    }

    @Override
    public void refill(Ink ink) {
        this.ink = ink;
        System.out.println("Refilled");
    }
}
```

### PenFactory (design discussed, not yet finalized in code)

The factory needed to be built up in stages because subclass constructors take different parameters:

- **Stage 1** (only Ball/Gel): a simple `switch` on `PenType` works fine, since both constructors share the same shape.
- **Stage 2** (Fountain added): a single flat method signature covering every possible field (`refill`, `nib`, `ink` all as params) starts to smell — callers must pass `null` for irrelevant fields depending on type, and the signature grows with every new pen type.
- **Stage 3** (better): bundle construction data into one `PenRequest` object; the factory switches on `request.getType()` and pulls out only the fields it needs per branch. Doesn't eliminate unused fields entirely, but contains the mess in one object instead of an ever-growing method signature.
- `getPenForType` should be **static** — `PenFactory` has no instance state, so every instance would behave identically; forcing callers to `new PenFactory()` first would be pure overhead. (Trade-off noted: static methods aren't mockable/overridable, which matters if testability via DI is ever a requirement.)

**Not yet written**: final `PenRequest` and `PenFactory` code — left as the next step to complete independently.

---

## Key Takeaways

- Clarify scope and domain facts *before* touching class design — several early contradictions (fountain pen refill, cap states) would have caused rework if coded first.
- Prefer **interfaces for capabilities** some types have and others don't (`RefillablePen`), rather than cramming shared-but-not-universal behavior into the abstract base.
- Watch for **abstract methods that are actually common logic** — forcing every subclass to reimplement identical logic is a bug magnet, not good OOP.
- Illegal-state handling needs to actually **stop execution** (`throw`/`return`) — printing a warning and continuing anyway is a silent bug.
- **Defensive copying** matters on both sides of a mutable field: the getter (protect internal state from external mutation) *and* the constructor (protect against the caller mutating what they passed in after the fact).
- Once inner objects (`Ink`, `Nib`) are made properly immutable, outer objects (`Refill`) that merely hold references to them don't need to defensively copy those references — the immutability already protects them.
- A factory's method signature should be shaped by *how much construction data varies across the types it builds* — when that varies a lot, prefer a bundled request/spec object over an ever-growing flat parameter list.