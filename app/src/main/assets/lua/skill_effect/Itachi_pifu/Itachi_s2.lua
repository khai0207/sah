--v3
--[[
Itachi_pifu
1
0
penxue
1
{default,gongji3,1,0,0,58,1.000000}
1
{default,0.000000,0.000000,-250.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}
0

0

1
{0,Itachi_pifu,2,0,0.000000,0.000000,58,0}
1
{default,gongji3_texie,1,0,0,58,1.000000}
1
{default,480.000000,200.000000,480.000000,200.000000,2,2,0,1,0.000000,0,0,0,0}
0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji3",1.000000,0,58,1}}, 
pos_sequence      = {{0.000000,0.000000,-250.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"Itachi_pifu",2,0,0.000000,0.000000,{{"gongji3_texie",1.000000,0,58,1}},{{480.000000,200.000000,480.000000,200.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0}}, 
bloodNum          = 0,
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
