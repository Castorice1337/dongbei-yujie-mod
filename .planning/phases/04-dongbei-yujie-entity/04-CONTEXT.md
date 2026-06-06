# Phase 04 Context: Dongbei Yujie Entity

## Domain
实现敌对的纸片人实体、AI、生成机制、生成蛋、掉落物以及客户端的广告牌（Billboard）渲染。

## Canonical Refs
- `.planning/PROJECT.md`
- `.planning/REQUIREMENTS.md`

## Code Context
- 已有的 Fabric Mod 骨架及客户端分离架构 (`src/client/kotlin`)。

## Decisions

### 广告牌旋转方式 (Billboard Rotation)
- **采用 Y 轴锁定旋转（圆柱形广告牌）**：实体只会在 Y 轴上旋转面向玩家。如果玩家从正上方俯视，实体看起来会变成一条薄线，这符合“纸片立牌”的特性和搞怪风格。

### 碰撞箱与缩放比例 (Hitbox & Scaling)
- **正方形大尺寸（2.5 × 2.5）**：视觉和碰撞箱采用 1:1 的正方形比例，宽高皆为 2.5 米，以此营造高大且具有压迫感的“方块立牌”效果。

### 近战攻击方式 (Melee Attack Style)
- **标准僵尸攻击**：采用原版僵尸的攻击节奏，贴身即可造成伤害，不附加特殊的攻击前摇或延迟。

### 生成限制 (Spawning Constraints)
- **严格孤狼模式**：每个玩家周围（例如 64 格半径内）最多只允许存在 1 个雨姐。这能避免多个实体堆叠导致 Daipai 效果叠加过强或引发掉帧卡顿。
