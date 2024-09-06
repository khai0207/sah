--v3
--[[
Itachi
1
0
penxue
1
{default,gongji6,1,0,0,90,1.000000}
0

0

0

1
{0,Itachi,2,0,0.000000,0.000000,90,0}
1
{default,gongji6_texiao,1,0,0,90,1.000000}
1
{default,600.000000,200.000000,600.000000,200.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,0.800000,0.800000,0.800000,0.800000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji6",1.000000,0,90,1}}, 
pos_sequence      = {}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"Itachi",2,0,0.000000,0.000000,{{"gongji6_texiao",1.000000,0,90,1}},{{600.000000,200.000000,600.000000,200.000000,2,2,0,1,0.000000,0,0,0,0}},{{0.800000,0.800000,0.800000,0.800000,0,0,1}},{},0}}, 
bloodNum          = 3,
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
