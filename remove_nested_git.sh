#!/bin/bash

# 自动清理嵌套 Git 仓库（非主仓库）
echo "🔍 正在查找嵌套 .git 目录..."

# 获取当前目录下的所有 .git 目录，排除最顶层的
find . -type d -name ".git" | while read gitdir; do
  # 跳过最顶层 .git
  if [ "$gitdir" != "./.git" ]; then
    echo "🧹 删除嵌套 Git 仓库: $gitdir"
    rm -rf "$gitdir"
  fi
done

echo "✅ 清理完成。你现在可以重新添加目录到 Git 中，例如："
echo "    git add ."

