# Phase 5: Linked Behavior and Resources - Context

**Gathered:** 2026-06-06
**Status:** Ready for planning

<domain>
## Phase Boundary

完成 Big Sweaty Foot / Daipai / 雨姐之间的跨系统交互行为，注册所有占位符音效事件，实现非叠加的雨姐 BGM，新增 Daipai/主手攻击命中时的 2D 大臭脚图标视觉反馈，制定公开发布资源策略，并清理重复纹理资源。

本阶段不添加新的游戏机制（如配置系统、新实体、新状态效果）。

</domain>

<decisions>
## Implementation Decisions

### 主手大臭脚强化行为 (LINK-01)
- **D-01:** 移除 `BigSweatyFootItem.postHit` 中对被击目标施加 Daipai I 的代码。取消对目标施加 Daipai Buff 的行为。
- **D-02:** 保留 `DaipaiLevelCalculator` 中主手持有 Big Sweaty Foot 时 +1 最终等级加成。这是 LINK-01 的核心「加强」。
- **D-03:** 保留 `postHit` 中的音效播放（`big_sweaty_foot_hit`）和随机攻击者专属消息。
- **D-04:** Daipai 范围伤害命中目标时和主手 Big Sweaty Foot 攻击命中目标时，在被击实体碰撞箱 3/4 高度居中位置渲染一个 2D 大臭脚图标（billboard 方式，类似雨姐实体的渲染），持续 1 秒后消失。
- **D-05:** 大臭脚命中图标是纯视觉反馈，不增加实际伤害或 Debuff。

### BGM 播放策略 (RES-04 / LINK-04)
- **D-06:** 使用 Minecraft 的 `MovingSoundInstance` 跟随距离玩家最近的雨姐实体位置播放 BGM。
- **D-07:** 全局只播放一个 BGM 实例，绝不叠加。
- **D-08:** 跟踪距离玩家最近的雨姐；当最近的雨姐消失或死亡时，如果世界里还有其他雨姐，自动切换到下一个最近的。
- **D-09:** 世界里没有任何雨姐时，BGM 完全停止。
- **D-10:** 无淡入淡出——雨姐出现时直接开始播放，消失时直接停止。简单粗暴，符合搞笑 Mod 风格。
- **D-11:** BGM 可听范围约 48~64 格（雨姐进入玩家视野时就能听到）。

### 占位符音效完整性 (RES-01)
- **D-12:** 注册 7 个新音效事件：`yujie_spawn`、`yujie_idle`、`yujie_attack`、`yujie_hurt`、`yujie_death`、`yujie_bgm`、`daipai_trigger`。
- **D-13:** 保留已有的 `big_sweaty_foot_hit`，最终共 8 个注册音效事件。
- **D-14:** 所有新增占位符音效使用最小静音 OGG 文件（确保游戏不会因找不到文件而报错）。
- **D-15:** `daipai_trigger` 音效在每 2 秒的 Daipai 范围伤害命中目标时播放。

### 资源打包与公开发布策略 (RES-02 / RES-03)
- **D-16:** 所有 PNG/OGG 资源全部内置打包到 Mod JAR 里，玩家不需要额外下载资源包。
- **D-17:** 目前部分资源还没到位。代码层面先用静音 OGG 占位，路径全部注册正确。等资源到位后在本阶段内替换文件即可。
- **D-18:** 清理重复实体纹理，只保留 `textures/entity/dongbei_yujie.png`，删除 `textures/entity/dongbeiyujie.png`。

### Agent's Discretion
- BGM 播放时与原版 Minecraft 背景音乐的共存关系交由 planner 自行决定。
- 静音 OGG 文件的具体格式和大小由 planner 决定，只需要是有效的 OGG Vorbis 文件。
- 2D 大臭脚命中图标的具体尺寸和透明度变化由 planner 决定，只需视觉效果明显且持续 1 秒。
- 雨姐实体的 `getAmbientSound()`、`getHurtSound()`、`getDeathSound()` 等覆写方法使用注册的对应占位符音效事件。

</decisions>

<canonical_refs>
## Canonical References

**Downstream agents MUST read these before planning or implementing.**

### Project Planning
- `.planning/PROJECT.md` — 项目身份、核心价值、约束条件和公开发布资源决策。
- `.planning/REQUIREMENTS.md` — Phase 5 需求 LINK-01 ~ LINK-04, RES-01 ~ RES-04。
- `.planning/ROADMAP.md` — Phase 5 目标和成功标准。

### Prior Phase Context
- `.planning/phases/02-daipai-core-system/02-CONTEXT.md` — Daipai 系统决策（D-01 ~ D-20），特别是伤害排除逻辑和等级计算。
- `.planning/phases/03-big-sweaty-foot-item/03-CONTEXT.md` — Big Sweaty Foot 双用途行为、恐吓逻辑、音效注册。
- `.planning/phases/04-dongbei-yujie-entity/04-CONTEXT.md` — 雨姐广告牌渲染、碰撞箱尺寸、攻击方式、生成限制。

### Codebase Maps
- `.planning/codebase/STACK.md` — Fabric/Kotlin/Minecraft 版本和构建工具链。
- `.planning/codebase/STRUCTURE.md` — 源集布局和客户端/服务端分离规则。

</canonical_refs>

<code_context>
## Existing Code Insights

### Reusable Assets
- `DongbeiYujieClientAudio.kt` — 空的 stub 对象，Phase 5 的 BGM 逻辑在此实现。
- `DongbeiYujieSounds.kt` — 已有 `BIG_SWEATY_FOOT_HIT` 注册模式，新增音效事件沿用此模式。
- `sounds.json` — 已有一个条目，新增占位符音效条目在此扩展。
- 雨姐实体 billboard 渲染器 (`src/client/kotlin/.../render/`) — 大臭脚命中图标可参考其 2D 渲染方式。
- `DaipaiStatusEffect.isDongbeiYujie` 扩展点 — 已经排除雨姐实体的 Daipai 伤害（LINK-04 部分已实现）。

### Established Patterns
- 音效注册：`DongbeiYujieSounds` 使用 `SoundEvent.of()` + `Registry.register()` 模式。
- 客户端/服务端分离：BGM（MovingSoundInstance）和命中图标渲染属于客户端代码，放在 `src/client`。
- 服务端事件：`BigSweatyFootAuraHandler` 使用 `ServerTickEvents.END_SERVER_TICK` 模式进行周期检查。

### Integration Points
- `BigSweatyFootItem.postHit()` — 移除 Daipai 施加代码，新增触发命中图标的服务端通知。
- `DaipaiStatusEffect.applyUpdateEffect()` — 在范围伤害命中逻辑中新增触发命中图标和 `daipai_trigger` 音效。
- `DongbeiYujieEntity` — 覆写 `getAmbientSound()`、`getHurtSound()`、`getDeathSound()` 使用占位符音效。
- `DongbeiYujieClient.onInitializeClient()` — 注册 BGM 追踪 tick 和命中图标渲染器。

</code_context>

<specifics>
## Specific Ideas

- 大臭脚命中图标使用类似雨姐实体的 2D billboard 渲染方式，面向摄像机。
- BGM 粗暴启停是刻意的——突然响起的 BGM 本身就是搞笑效果的一部分。
- 所有占位符音效用最小静音 OGG，确保资源到位前游戏可以正常运行且不报错。
- 资源替换是本阶段的最后一步，在用户提供最终文件后手动替换文件即可。

</specifics>

<deferred>
## Deferred Ideas

None — 讨论全程保持在阶段范围内。

</deferred>

---

*Phase: 5-Linked Behavior and Resources*
*Context gathered: 2026-06-06*
