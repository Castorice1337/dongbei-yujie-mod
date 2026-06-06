# Phase 3: Big Sweaty Foot Item - Discussion Log

**Gathered:** 2026-06-06
**Status:** Complete

## Areas Discussed

### Item dual behavior and slots
- **Question:** How should the dual-purpose weapon/boots item be implemented?
- **Selected:** Extend the item with attribute modifiers and equipment ticking handlers to support both main hand and feet slot usage.
- **Notes:** Main hand uses unbreakable attributes, 12 attack damage, and 1.8 attack speed. Feet slot uses 6 armor, unbreakable attributes, and handles curse and buff application.

### Hit Feedback
- **Question:** What should happen when hitting a living entity?
- **Selected:** Play the custom `big_sweaty_foot_hit` sound event and send a random chat message to the attacker (localizable).
- **Notes:** Messages are attacker-only by default to prevent spamming public server chat.

### Aura & Mob Avoidance (Fear)
- **Question:** How should the mob avoidance/fear behavior work?
- **Selected:** While wearing Big Sweaty Foot boots and carrying Daipai:
  - Periodically search for nearby mobs.
  - Clear their attack targets if they are targeting the wearer.
  - Prevent them from re-acquiring the wearer as a target.
  - Direct close mobs to flee from the wearer's position.
- **Notes:** Avoidance target-rejection must exclude other players and Dongbei Yujie entities. The navigation should be smooth and avoid stuttering.

### Resource Boundaries
- **Question:** How should custom textures, sounds, and models be handled?
- **Selected:** Wire all code to the correct namespace and paths (e.g. `dongbeiyujie`), but keep assets as placeholders or default textures/sounds to ensure public-release safety.

---

*Discussion log for Phase 3: Big Sweaty Foot Item*
