--v3
--[[
gelimuqiao
1
0
penxue
1
{default,gongji6,1,0,0,162,1.000000}
1
{default,0.000000,0.000000,-280.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}
0

0

1
{0,gelimuqiao,2,0,0.000000,0.000000,162,0}
1
{default,texiao,1,0,0,162,1.000000}
1
{default,0.000000,50.000000,0.000000,50.000000,0,0,0,1,0.000000,0,0,0,0}
1
{default,0.900000,0.900000,0.900000,0.900000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji6",1.000000,0,162,1}}, 
pos_sequence      = {{0.000000,0.000000,-280.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"gelimuqiao",2,0,0.000000,0.000000,{{"texiao",1.000000,0,162,1}},{{0.000000,50.000000,0.000000,50.000000,0,0,0,1,0.000000,0,0,0,0}},{{0.900000,0.900000,0.900000,0.900000,0,0,1}},{},0}}, 
bloodNum          = 6,
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
