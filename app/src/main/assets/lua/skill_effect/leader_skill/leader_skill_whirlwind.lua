-- 1、動作是連續播放普通攻擊的動作
--    按順序攻擊三個目標（後端腳本通知的）

local skillTest = {
    attack_type       = "yuangong", --jingong  yuangong  用於當前attack屬於那種攻擊
    attack_equence    = {{"daiji",0.5},{"daiji",0.5}}, --動作序列 加在攻擊者身上
	attack_equence2   = {{"mofa",1},{"mofa",1},{"mofa",1},{"mofa",1},{"mofa",1},{"mofa",1},{"mofa",1},{"mofa",1},{"mofa",1},{"mofa",1},{"mofa",1},{"mofa",1},{"mofa",1}}, --動作序列 加在攻擊者身上
    flyAnim           = "xuanfeng", --移動動畫  例如：火球
    flyAnim_equence   = {{"impact",1}}, --移動動畫播放的動作序列
    par               = "",--粒子特效
    start_p           = {0,0,0},--相對起始點，點有三個信息（相對對象，x，y），相對對像（0，為施法者，1為受法者,2 位置坐標） 他的值將決定 動畫出現在己方 還是對方 身上
    end_p             = {2,0,0},--相對結束點，同上
    fly_v             = 150, --移動動畫飛行速度，單位（像素/幀）
    fly_h             = 0, --移動動畫飛行最大相對高度，相對於飛行距離, 大於0為凸曲線，小於0為凹曲線
    hurtAnim          = "daoguang", --受傷效果
    hurtAnim_equence  = {{"impact",1}},--受傷效果動作序列
    skillCallFunc     = function(self,battleSkill,battleTable) -- 技能的入口方法
        local skillData = battleTable.m_currentFrameData;  -- 後端傳的當前技能的數據
        local function attackEnd()
        end
        battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});--順序攻擊一個或者多個
    end,
}
return skillTest