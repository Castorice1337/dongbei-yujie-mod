---
phase: 2
slug: daipai-core-system
status: approved
nyquist_compliant: false
wave_0_complete: true
created: "2026-06-06T05:44:00Z"
---

# Phase 02 — Validation Strategy

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
| Status Effect Registration | DAIP-01 | No Fabric test mod | Checked via UAT in-game `/effect` command |
| Final Level Calculation | DAIP-02 | No Fabric test mod | Checked via UAT in-game mechanics |
| Periodic Evaluation Cadence | DAIP-03 | No Fabric test mod | Checked via UAT in-game ticks |
| Range and Target Filtering | DAIP-04 | No Fabric test mod | Checked via UAT in-game logic |
| Damage Attribution | DAIP-05 | No Fabric test mod | Checked via UAT in-game death messages |
| Enchantment Registration | ENCH-01 | No Fabric test mod | Checked via UAT in-game `/give` command |
| Enchantment Level Application | ENCH-02 | No Fabric test mod | Checked via UAT in-game mechanics |
| Localized Name | ENCH-03 | No Fabric test mod | Checked via UAT in-game language |
| Particle Feedback | ENCH-04 | No Fabric test mod | Checked via UAT in-game visual effects |

---

## Validation Sign-Off

- [x] All tasks have `<automated>` verify or Wave 0 dependencies
- [x] Sampling continuity: no 3 consecutive tasks without automated verify
- [x] Wave 0 covers all MISSING references
- [x] No watch-mode flags
- [x] Feedback latency < 15s

**Approval:** approved 2026-06-06
