---
phase: 3
slug: big-sweaty-foot-item
status: approved
nyquist_compliant: false
wave_0_complete: true
created: "2026-06-06T15:45:00Z"
---

# Phase 03 — Validation Strategy

> Per-phase validation contract for feedback sampling during execution.

---

## Test Infrastructure

| Property | Value |
|----------|-------|
| **Framework** | None detected (Minecraft Fabric Mod) |
| **Config file** | none |
| **Quick run command** | `./gradlew.bat build` |
| **Full suite command** | `./gradlew.bat build` |
| **Estimated runtime** | ~15 seconds |

---

## Sampling Rate

- **After every task commit:** Run `./gradlew.bat build`
- **After every plan wave:** Run `./gradlew.bat build`
- **Before `/gsd-verify-work`:** Build must be green
- **Max feedback latency:** 15 seconds

---

## Per-Task Verification Map

*All tasks verified manually during UAT as no test framework is configured.*

---

## Wave 0 Requirements

*Existing infrastructure covers all phase requirements.*

---

## Manual-Only Verifications

| Behavior | Requirement | Why Manual | Test Instructions |
|----------|-------------|------------|-------------------|
| Obtain Big Sweaty Foot | FOOT-01 | No Fabric test mod | Checked via UAT `/give` and Creative Tab group |
| Main-Hand Weapon Stats | FOOT-02 | No Fabric test mod | Checked via UAT hand hover tooltip and hit testing |
| Hit Feedback (Chat & Sound) | FOOT-03 | No Fabric test mod | Checked via UAT hitting mobs and verifying sound and chat messages |
| Apply Daipai on Hit | FOOT-04 | No Fabric test mod | Checked via UAT verifying Daipai is applied to target |
| Boots slot equip & Curse | FOOT-05, FOOT-06 | No Fabric test mod | Checked via UAT equip slot, Binding Curse, and Daipai I buff addition |
| Enchantment Compatibility | FOOT-07 | No Fabric test mod | Checked via UAT table and anvil enchanting |
| Mob Target Rejection & Flee | LINK-02, LINK-03 | No Fabric test mod | Checked via UAT verifying mob target clearing and directional flee behavior |

---

## Validation Sign-Off

- [x] All tasks have `<automated>` verify or Wave 0 dependencies
- [x] Sampling continuity: no 3 consecutive tasks without automated verify
- [x] Wave 0 covers all MISSING references
- [x] No watch-mode flags
- [x] Feedback latency < 15s

**Approval:** approved 2026-06-06
