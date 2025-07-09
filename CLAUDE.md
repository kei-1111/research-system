# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## プロジェクト概要

このプロジェクトは、日本の歴史的人口データとイベントを視覚化するKotlin Multiplatform Composeプロジェクトです。アプリケーションは、イベントノード、特徴語ノード、詳細なイベント表示を含むインタラクティブなチャートを作成します。WebAssemblyをターゲットとし、https://kei-1111.github.io/research-system/ でWeb展開されています。

## 【MUST】Gemini活用

### 三位一体の開発原則
人間の**意思決定**、Claude Codeの**分析と実行**、Gemini MCPの**検証と助言**を組み合わせ、開発の質と速度を最大化する：
- **人間 (ユーザー)**：プロジェクトの目的・要件・最終ゴールを定義し、最終的な意思決定を行う**意思決定者**
    - 反面、具体的なコーディングや詳細な計画を立てる力、タスク管理能力ははありません。
- **Claude Code**：高度なタスク分解・高品質な実装・リファクタリング・ファイル操作・タスク管理を担う**実行者**
    - 指示に対して忠実に、順序立てて実行する能力はありますが、意志がなく、思い込みは勘違いも多く、思考力は少し劣ります。
- **Gemini MCP**：API・ライブラリ・エラー解析など**コードレベル**の技術調査・Web検索 (Google検索) による最新情報へのアクセスを行う**コード専門家**
    - ミクロな視点でのコード品質・実装方法・デバッグに優れますが、アーキテクチャ全体の設計判断は専門外です。

### 壁打ち先の自動判定ルール
- **ユーザーの要求を受けたら即座に壁打ち**を必ず実施
- 壁打ち結果は鵜呑みにしすぎず、1意見として判断
- 結果を元に聞き方を変えて多角的な意見を抽出するのも効果的

### 主要な活用場面
1. **実現不可能な依頼**: Claude Codeでは実現できない要求への対処 (例: `今日の天気は？`)
2. **前提確認**: ユーザー、Claude自身に思い込みや勘違い、過信がないかどうか逐一確認 (例: `この前提は正しいか？`）
3. **技術調査**: 最新情報・エラー解決・ドキュメント検索・調査方法の確認（例: `Rails 7.2の新機能を調べて`）
4. **設計検証**: アーキテクチャ・実装方針の妥当性確認（例: `この設計パターンは適切か？`）
5. **問題解決**: Claude自身が自力でエラーを解決できない場合に対処方法を確認 (例: `この問題の対処方法は？`)
6. **コードレビュー**: 品質・保守性・パフォーマンスの評価（例: `このコードの改善点は？`）
7. **計画立案**: タスクの実行計画レビュー・改善提案（例: `この実装計画の問題点は？`）
8. **技術選定**: ライブラリ・手法の比較検討 （例: `このライブラリは他と比べてどうか？`）
9. **実装前リスク評価**: 複雑な実装着手前の事前リスク確認・落とし穴の事前把握（例: `ReactとD3.jsの組み合わせでよくある問題は？`）
10. **設計判断の事前検証**: アーキテクチャ決定前の多角的検証・技術的負債の予防（例: `マイクロサービス化の判断は適切か？`）

## ビルドコマンド

```bash
# プロジェクトをビルド
./gradlew build

# detekt（リントと静的解析）を実行
./gradlew detekt

# WebAssembly用にビルド
./gradlew wasmJsBrowserDevelopmentWebpack

# 開発サーバーを実行
./gradlew wasmJsBrowserDevelopmentRun
```

## 開発コマンド

```bash
# クリーンビルド
./gradlew clean

# 依存関係のアップデートを確認
./gradlew dependencyUpdates

# detektを自動修正付きで実行
./gradlew detektMain --auto-correct
```

## アーキテクチャ概要

### 技術スタック
- **Kotlin Multiplatform**: WebAssembly (wasmJs)をターゲット
- **Compose Multiplatform**: Material 3を使用したUIフレームワーク
- **Koin**: 依存性注入
- **Kotlinx Serialization**: 歴史データのJSON解析
- **KoalaPlot**: チャートレンダリングライブラリ
- **Detekt**: 静的解析とリント

### 主要アーキテクチャパターン

**MVVM with BaseViewModel**: すべてのViewModelは `BaseViewModel<S, E>` を継承し、以下を提供します：
- `StateFlow<S>` による状態管理
- `SharedFlow<E>` によるイベント処理
- 状態更新とイベント発行のヘルパーメソッド

**リポジトリパターン**: `HistoricalEventsRepository` などのリポジトリによるデータアクセス：
- `Res.readBytes()` を使用してリソースからJSONデータを読み込み
- `Result<T>` 戻り値型によるキャッシュ実装
- クエリメソッドの提供（年別、テキスト検索など）

**依存性注入**: Koinモジュール（`PopulationModule` を参照）を使用：
- リポジトリをシングルトンとして登録
- ViewModelをファクトリとして登録

### 主要コンポーネント

**データレイヤー**:
- `HistoricalEventsRepository`: JSONからの歴史イベントデータ管理
- モデル: `HistoricalEvent`, `MajorEvent`, `CharacteristicWord`, `EventNode`
- `data/` パッケージ内の人口とイベントの静的データコレクション

**UIレイヤー**:
- `PopulationScreen`: チャート可視化のメインスクリーン
- カスタムコンポーネント: `EventNode`, `CharacteristicWordNode`, `EventDetails`
- `PopulationViewModel`: チャートインタラクションとデータロードの状態処理

**チャートシステム**:
- KoalaPlotを使用したエリアチャートとデータ可視化
- `Offset` 計算によるカスタムノード配置
- イベント詳細のためのインタラクティブなホバー状態とクリック処理

### 状態管理

UIの状態は `UiState` を継承したデータクラスで管理されます：
- `PopulationUiState`: チャートデータ、ロード状態、ユーザーインタラクションを管理
- 状態更新は `copy()` を使用したイミュータブルパターンを使用
- イベントは `UiEvent` を継承したsealed classで処理

### リソース管理

歴史データは `composeResources/files/historical_events.json` に保存され、Compose Resources APIを使用して `@OptIn(ExperimentalResourceApi::class)` で読み込まれます。

## コード構成

```
composeApp/src/commonMain/kotlin/org/example/project/
├── data/                    # 静的データとリポジトリ
├── di/                      # 依存性注入モジュール
├── ktx/                     # Kotlin拡張
├── model/                   # データモデル
├── ui/
│   ├── base/               # ベースクラス（BaseViewModel, UiState, UiEvent）
│   ├── component/          # 再利用可能なUIコンポーネント
│   ├── feature/population/ # 人口スクリーンとViewModel
│   └── theme/              # テーマとスタイリング
└── utils/                  # ユーティリティ関数
```

## 重要な開発ノート

- プロジェクトは `config/detekt/detekt.yml` のカスタム設定でDetektを使用
- ビルド設定で自動修正が有効
- WebAssemblyターゲットには静的ファイル配信のための特定のwebpack設定が必要
- 歴史イベントデータは日本語テキストを含み、適切な文字エンコーディングが必要
- チャート位置決めはカスタム拡張関数を通じてピクセル-dp変換を使用
- **新しいファイルを作成した場合は、必ず `git add <filename>` を実行してコミット用にステージする**