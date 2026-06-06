# Phase 5: Linked Behavior and Resources - Discussion Log

> **Audit trail only.** Do not use as input to planning, research, or execution agents.
> Decisions are captured in CONTEXT.md — this log preserves the alternatives considered.

**Date:** 2026-06-06
**Phase:** 5-Linked Behavior and Resources
**Areas discussed:** 主手大臭脚强化行为, BGM 播放策略, 占位符音效完整性, 资源打包与公开发布策略

---

## 主手大臭脚强化行为

### Q1: 主手持有时如何「加强」Daipai 攻击？

| Option | Description | Selected |
|--------|-------------|----------|
| 保持现状 | +1 等级 + 施加 Daipai I 已足够 | |
| 提高施加等级 | 主手命中时施加 Daipai II 或 III | |
| 延长效果时间 | 维持 Daipai I 但从 5 秒加长到 10~15 秒 | |
| 叠加伤害脉冲 | 命中时额外触发即时 Daipai 范围伤害 | |

**User's choice:** 取消掉对目标施加带派（自由文本输入）
**Notes:** 用户希望完全移除 postHit 中对目标施加 Daipai I 的行为，只保留 +1 等级加成。

### Q2: 命中时的音效和随机聊天消息还保留吗？

| Option | Description | Selected |
|--------|-------------|----------|
| 保留 | 命中时仍然播放音效和发送随机消息 | ✓ |
| 删除 | 命中时不再播放音效和消息 | |

**User's choice:** 保留
**Notes:** 无

### Q3: 需要补充命中时的额外效果吗？

| Option | Description | Selected |
|--------|-------------|----------|
| 不需要 | 12 攻击伤害 + 等级加成足够 | |
| 短暂减速 | 被击目标减速 2~3 秒 | |
| 击退加强 | 比普通武器更大的击退距离 | |

**User's choice:** 命中时在敌方碰撞箱 3/4 高度居中渲染大臭脚 2D 图标 1 秒（自由文本输入）
**Notes:** 用户提出创意方案——Daipai 范围伤害和主手攻击命中时都在被击实体身上渲染 2D 大臭脚 billboard 图标，持续 1 秒，纯视觉反馈。

### Q4: 两种命中都显示大臭脚图标？

| Option | Description | Selected |
|--------|-------------|----------|
| 两种都显示 | Daipai 范围伤害命中 + 主手攻击命中都显示 | ✓ |
| 仅主手攻击 | 只在主手攻击命中时显示 | |
| 仅 Daipai 范围伤害 | 只在范围伤害命中时显示 | |

**User's choice:** 两种命中都显示
**Notes:** 无

---

## BGM 播放策略

### Q1: BGM 播放模式？

| Option | Description | Selected |
|--------|-------------|----------|
| MovingSoundInstance | 跟随雨姐实体位置，3D 空间音效 | ✓ |
| 全局 BGM | 固定音量背景音乐 | |
| 你决定 | 满足基本需求即可 | |

**User's choice:** MovingSoundInstance，并且全局只播一个最近的雨姐 BGM
**Notes:** 用户指定有多个雨姐时只跟踪最近的一个播放。

### Q2: 淡入淡出？

| Option | Description | Selected |
|--------|-------------|----------|
| 无淡入淡出 | 粗暴启停，符合搞笑风格 | ✓ |
| 淡入淡出 | 1~2 秒平滑过渡 | |
| 你决定 | | |

**User's choice:** 无淡入淡出
**Notes:** 简单粗暴是刻意的搞笑效果。

### Q3: 与原版背景音乐的关系？

| Option | Description | Selected |
|--------|-------------|----------|
| 停止原版音乐 | 雨姐 BGM 独占音乐槽 | |
| 不停止原版音乐 | 两者可以同时播放 | |
| 你决定 | | ✓ |

**User's choice:** 你决定
**Notes:** 交由 planner 决定。

### Q4: BGM 可听范围？

| Option | Description | Selected |
|--------|-------------|----------|
| 很远 (48~64格) | 雨姐进入视野就能听到 | ✓ |
| 中等 (24~32格) | 比较近才能听到 | |
| 近 (16格) | 只有很近时才能听到 | |
| 你决定 | | |

**User's choice:** 很远（48~64格）
**Notes:** 无

---

## 占位符音效完整性

### Q1: 需要注册哪些音效事件？

| Option | Description | Selected |
|--------|-------------|----------|
| yujie_spawn | 雨姐生成音效 | ✓ |
| yujie_idle | 雨姐待机音效 | ✓ |
| yujie_attack | 雨姐攻击音效 | ✓ |
| yujie_hurt | 雨姐受伤音效 | ✓ |
| yujie_death | 雨姐死亡音效 | ✓ |
| daipai_trigger | Daipai 触发音效 | ✓ |
| yujie_bgm | 雨姐 BGM | ✓ |

**User's choice:** 全部选中
**Notes:** 无

### Q2: 占位符文件类型？

| Option | Description | Selected |
|--------|-------------|----------|
| 静音 OGG | 最小的静音 OGG 文件 | ✓ |
| 原版替代 | 在 sounds.json 中指向原版音效 | |
| 你决定 | | |

**User's choice:** 静音 OGG
**Notes:** 确保游戏不会因找不到文件而报错。

### Q3: daipai_trigger 播放时机？

| Option | Description | Selected |
|--------|-------------|----------|
| 范围伤害命中时 | 每 2 秒范围伤害命中目标时播放 | ✓ |
| Buff 被施加时 | 实体获得 Daipai Buff 瞬间播放 | |
| 两种都要 | 施加时播一次，每次范围伤害也播 | |
| 你决定 | | |

**User's choice:** 范围伤害命中时
**Notes:** 无

---

## 资源打包与公开发布策略

### Q1: 当前 PNG 资源状态？

| Option | Description | Selected |
|--------|-------------|----------|
| 全部原创/已授权 | 可以安全内置 | |
| 部分占位符 | 需要后续替换 | |
| 全部占位符 | 最终发布前全部替换 | |

**User's choice:** 一些资源还没到位，等到位了再收工
**Notes:** 资源替换在本阶段范围内完成，但先搭好代码框架。

### Q2: 打包方式？

| Option | Description | Selected |
|--------|-------------|----------|
| 内置资源 | PNG/OGG 直接打包在 Mod JAR 里 | ✓ |
| 资源包分离 | Mod JAR 只含代码和占位符 | |
| 先内置后续再说 | | |

**User's choice:** 内置资源
**Notes:** 玩家不需要额外下载。

### Q3: 资源到位后的集成方式？

| Option | Description | Selected |
|--------|-------------|----------|
| 手动替换 | 路径正确后用户手动放文件 | |
| 在本阶段内一起做 | 等资源到位后通过 GSD 完成 | ✓ |

**User's choice:** 就放这一步里，先不 execute，等会我们一起做
**Notes:** Phase 5 包含资源集成步骤，先搭代码框架。

### Q4: 重复实体纹理清理？

| Option | Description | Selected |
|--------|-------------|----------|
| 只保留 dongbei_yujie.png | 删除 dongbeiyujie.png | ✓ |
| 只保留 dongbeiyujie.png | 删除 dongbei_yujie.png | |
| 两个都保留 | 不同用途 | |
| 不确定 | 等资源到位后再说 | |

**User's choice:** 只保留 dongbei_yujie.png
**Notes:** 无

---

## Agent's Discretion

- BGM 与原版背景音乐的共存关系
- 静音 OGG 文件的格式和大小
- 2D 大臭脚命中图标的尺寸和透明度变化

## Deferred Ideas

None — 讨论全程保持在阶段范围内。
