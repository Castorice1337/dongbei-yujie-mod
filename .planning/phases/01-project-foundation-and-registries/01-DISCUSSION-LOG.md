# Phase 1: Project Foundation and Registries - Discussion Log

> **Audit trail only.** Do not use as input to planning, research, or execution agents.
> Decisions are captured in CONTEXT.md — this log preserves the alternatives considered.

**Date:** 2026-06-06
**Phase:** 1-Project Foundation and Registries
**Areas discussed:** Public metadata, Foundation skeleton, Resource and datagen conventions

---

## Area Selection

| Option | Description | Selected |
|--------|-------------|----------|
| 公开元数据 | 模组名、描述、作者/联系信息、license 和 README 口径，影响公开发布第一印象。 | ✓ |
| 骨架厚度 | Phase 1 是只改入口和注册结构，还是先铺完整 registry/datagen 包骨架。 | |
| 资源约定 | 资源路径、语言文件、占位资源和 datagen 输出先做到什么程度。 | |

**User's choice:** 公开元数据
**Notes:** Other gray areas were still discussed after public metadata was locked because they were needed to make the context decision-complete for Phase 1.

---

## Public Metadata

| Option | Description | Selected |
|--------|-------------|----------|
| 东北雨姐趣味模组 | 中文玩家一眼懂，贴合整活主题；英文 metadata 可用 Dongbei Yujie Funny Mod。 | ✓ |
| Dongbei Yujie | 更短更国际化，但中文梗味会弱一点。 | |
| 东北雨姐整活模组 | 更口语更梗，但公开发布显得随意一些。 | |

**User's choice:** 东北雨姐趣味模组
**Notes:** English/public fallback is `Dongbei Yujie Funny Mod`.

| Option | Description | Selected |
|--------|-------------|----------|
| 代码 MIT | 代码开源友好；资源单独声明为占位/授权素材，不继承代码 license。 | |
| 继续 CC0 | 最宽松，但不适合未来混入自制或授权资源时的边界表达。 | |
| 暂不发布 | 先把 metadata 标为 All Rights Reserved/私用，等资源确定后再改。 | |
| GPL3.0 | User-provided freeform choice. | ✓ |

**User's choice:** GPL3.0
**Notes:** Captured as GPL-3.0 for code, with resource licensing kept explicit and public-release-safe.

| Option | Description | Selected |
|--------|-------------|----------|
| 占位但真实 | 作者用当前项目名/昵称占位，sources 留空或指向待定，去掉 Fabric example 链接。 | |
| 完全留空 | 只保留必要字段，不提供 homepage/sources。 | |
| 先用仓库链接 | 如果本地 git 有 remote 就用 remote URL；没有则写待定。 | |
| Castorice1337 details | User-provided freeform author/contact/source details. | ✓ |

**User's choice:** Castorice1337, `ilovecastoriceforever@gmail.com`, `bilibili@MyColumbina`, repository link.
**Notes:** Repository remote was discovered as `https://github.com/Castorice1337/dongbei-yujie-mod.git`.

| Option | Description | Selected |
|--------|-------------|----------|
| 梗但克制 | 说明这是整活模组，同时清楚写出雨姐实体、大汗脚、带派机制，适合公开发布。 | ✓ |
| 纯整活 | 文案更像段子，梗味最足，但 Mod 页面可读性较弱。 | |
| 偏正式 | 像普通玩法模组说明，降低梗浓度，更利于英文/公开平台理解。 | |

**User's choice:** 梗但克制
**Notes:** README should be a full project description, not a minimal metadata-only update.

---

## Foundation Skeleton

| Option | Description | Selected |
|--------|-------------|----------|
| 完整空骨架 | 为 items/entities/effects/enchantments/sounds/spawning/datagen 建好命名对象和 init 调用，但不注册具体玩法内容。 | ✓ |
| 最小骨架 | 只改 metadata、入口日志和 README；registry 文件到对应阶段再创建。 | |
| 半骨架 | 只建 items/sounds/datagen 这类最早会用到的结构，其余后续阶段补。 | |

**User's choice:** 完整空骨架
**Notes:** Must not register concrete gameplay content in Phase 1.

| Option | Description | Selected |
|--------|-------------|----------|
| 删除/停用 | 移除无行为且 required 的示例 mixin，降低启动风险；后续需要真实 mixin 再加。 | ✓ |
| 保留但改名 | 保留 mixin 管线但改成项目名，适合预期很快需要 mixin 的情况。 | |
| 暂不处理 | 让 example mixins 留到后续阶段，Phase 1 只做 metadata/registry。 | |

**User's choice:** 删除/停用
**Notes:** This addresses a known codebase concern.

| Option | Description | Selected |
|--------|-------------|----------|
| 保留并接线 | 保留 fabric.mod.json 里的 client/datagen entrypoints，并让它们调用空注册对象。 | |
| 只保留 main | 暂时移除 client/datagen metadata，等 Phase 4/5 再恢复。 | ✓ |
| 保留原状 | 只改注释/日志，不建立调用结构。 | |

**User's choice:** 只保留 main
**Notes:** Follow-up resolved this as "保留文件不注册": keep client/datagen Kotlin files and conventions, but remove metadata entrypoint registration for now.

---

## Resource and Datagen Conventions

| Option | Description | Selected |
|--------|-------------|----------|
| 骨架+说明 | 保留 datagen 类和 provider 包约定，README/AGENTS/Context 说明后续由 datagen 产出模型、语言、loot、sounds。 | ✓ |
| 注册空 provider | 创建 provider 类但不输出具体数据，可能增加无意义代码。 | |
| 推迟 datagen | Phase 1 只记录资源路径约定，datagen 具体结构到资源阶段再做。 | |

**User's choice:** 骨架+说明
**Notes:** Do not add empty no-op providers in Phase 1.

| Option | Description | Selected |
|--------|-------------|----------|
| 声明路径不放资源 | 在 context/README 中锁定 assets/dongbeiyujie 下的路径命名，实际 PNG/OGG 由后续阶段加入。 | |
| 放最小占位 | 加入简单占位 PNG/声音路径，能早测资源加载但会增加本阶段范围。 | |
| 只保留 icon | 除了现有 icon 不碰资源目录，后续阶段自由决定路径。 | |
| 用 image2 模型生成，之后替换 | User-provided freeform choice for later resource phase. | ✓ |

**User's choice:** 用 image2 模型生成，之后替换
**Notes:** Captured as deferred to the later resource phase. Phase 1 only locks conventions.

| Option | Description | Selected |
|--------|-------------|----------|
| 中英都预留 | 后续资源/datagen 生成 zh_cn 和 en_us；中文为主，英文用于公开发布可读性。 | ✓ |
| 只中文 | 更贴近梗源，但公开平台和英文环境显示较弱。 | |
| 只英文 | 更国际化，但中文梗味不足。 | |

**User's choice:** 中英都预留
**Notes:** Chinese is primary; English supports public release readability.

---

## the agent's Discretion

- Exact package/object names for registry skeletons are left to the planner.
- Exact README wording is left to the planner within the locked tone and metadata.
- Exact mixin cleanup mechanism is left to the planner, as long as required no-op mixins are removed.

## Deferred Ideas

- image2-generated placeholder or initial art belongs in the later resource phase.
- Concrete gameplay content belongs in Phases 2 through 5.
