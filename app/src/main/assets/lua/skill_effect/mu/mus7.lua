--v3
--[[
mu
1
0
penxue
2
{default,qianjin,1,0,0,8,1.000000}{default,gongji8,1,1,9,156,1.000000}
1
{default,0.000000,0.000000,280.000000,250.000000,0,2,0,1,0.000000,0,0,0,0}
0

0

1
{0,mu,0,0,0.000000,0.000000,147,0}
1
{default,texiao1,1,1,0,147,1.000000}
0

0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji8",1.000000,9,156,1}}, 
pos_sequence      = {{0.000000,0.000000,280.000000,250.000000,0,2,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"mu",0,0,0.000000,0.000000,{{"texiao1",1.000000,0,147,1}},{},{},{},0}}, 
bloodNum          = 9,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
