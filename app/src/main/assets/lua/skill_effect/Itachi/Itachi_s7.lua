--v3
--[[
Itachi
1
0
penxue
1
{default,gongji8,1,0,0,43,1.000000}
1
{default,-300.000000,0.000000,-300.000000,0.000000,1,1,0,1,0.000000,0,0,0,0}
0

0

1
{0,Itachi,0,0,0.000000,0.000000,43,0}
0

0

0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji8",1.000000,0,43,1}}, 
pos_sequence      = {{-300.000000,0.000000,-300.000000,0.000000,1,1,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"Itachi",0,0,0.000000,0.000000,{},{},{},{},0}}, 
bloodNum          = 2,
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
