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
