// components/theme.ts
import { createSystem, defineConfig } from "@chakra-ui/react"
import { defaultConfig } from "@chakra-ui/react"

const config = defineConfig({
  globalCss: {
    html: {
      colorPalette: "teal", // 기본 색상 팔레트를 변경
    },
  },
  
})

export const system = createSystem(defaultConfig, config)